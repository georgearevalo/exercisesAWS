package com.ejemplo.cognito;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminSetUserPasswordRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.DeliveryMediumType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InvalidPasswordException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.MessageActionType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.TooManyRequestsException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserNotFoundException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameExistsException;

public class CognitoUserManagementExample {
// --- REEMPLAZA ESTOS VALORES CON LOS TUYOS ---
    private static final String USER_POOL_ID = "tu_user_pool_id"; // Ej. us-east-1_xxxxxxxxx
    private static final String APP_CLIENT_ID = "tu_app_client_id"; // Ej. abcdefg1234567890
    private static final Region AWS_REGION = Region.US_EAST_1; // Reemplaza con tu región

    public static void main(String[] args) {
        // 1. Configurar el cliente de CognitoIdentityProvider
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(AWS_REGION)
                .build();

        // 2. Crear un nuevo usuario
        // NOTA: adminCreateUser no envía el correo de verificación por defecto,
        // el usuario queda en estado 'FORCE_CHANGE_PASSWORD' o 'CONFIRMED' si lo seteas.
        // Para que el usuario pueda iniciar sesión, su estado debe ser 'CONFIRMED'.
        // Puedes verificar la política de contraseña de tu User Pool.
        createUser(cognitoClient, "newuser@example.com", "TemporalPass123!", "New User", "Cognito");

        // 3. Establecer la contraseña del usuario (si es necesario y el estado lo permite)
        // Solo si 'adminCreateUser' no lo marcó como CONFIRMED directamente y necesitas setearla.
        // adminSetUserPassword(cognitoClient, "newuser@example.com", "NuevaPassSegura!", false); // false para no forzar cambio

        // 4. Listar usuarios
        listAllUsers(cognitoClient);

        // 5. Consultar detalles de un usuario específico
        getUserDetails(cognitoClient, "newuser@example.com");

        // 6. Cerrar el cliente
        cognitoClient.close();
    }

    /**
     * Crea un nuevo usuario en Cognito User Pool.
     * Utiliza AdminCreateUser, que es ideal para administración desde el backend.
     * Para registro de usuarios directos desde la app, usaría 'signUp'.
     */
    public static void createUser(CognitoIdentityProviderClient cognitoClient, String username, String password, String givenName, String familyName) {
        System.out.println("\n--- Creando nuevo usuario: " + username + " ---");

        // Atributos obligatorios: email
        // Otros atributos: given_name, family_name
        List<AttributeType> userAttributes = new ArrayList<>();
        userAttributes.add(AttributeType.builder().name("email").value(username).build());
        userAttributes.add(AttributeType.builder().name("given_name").value(givenName).build());
        userAttributes.add(AttributeType.builder().name("family_name").value(familyName).build());

        AdminCreateUserRequest request = AdminCreateUserRequest.builder()
                .userPoolId(USER_POOL_ID)
                .username(username)
                .userAttributes(userAttributes)
                .temporaryPassword(password) // Cognito genera una contraseña temporal si no se especifica.
                                             // Si la especificas, el usuario deberá cambiarla al iniciar sesión.
                                             // A veces, puedes omitir esta y usar AdminSetUserPassword después.
                .messageAction(MessageActionType.SUPPRESS) // SUPPRESS para no enviar email de bienvenida/verificación.
                                                           // Puedes usar RESEND para que envíe correo de bienvenida.
                .desiredDeliveryMediums(DeliveryMediumType.EMAIL) // Opcional: cómo quieres que se envíen mensajes (MFA, etc.)
                .forceAliasCreation(false) // Permite crear un alias si ya existe un atributo único
                .build();

        try {
            AdminCreateUserResponse response = cognitoClient.adminCreateUser(request);
            System.out.println("Usuario '" + response.user().username() + "' creado exitosamente.");
            System.out.println("Estado del usuario: " + response.user().userStatusAsString());
            System.out.println("Correo verificado: " + response.user().attributes().stream()
                                    .filter(attr -> "email_verified".equals(attr.name()))
                                    .map(AttributeType::value).findFirst().orElse("N/A"));

        } catch (UsernameExistsException e) {
            System.err.println("Error: El usuario '" + username + "' ya existe. " + e.getMessage());
        } catch (TooManyRequestsException e) {
            System.err.println("Error: Demasiadas solicitudes. " + e.getMessage());
        } catch (CognitoIdentityProviderException e) {
            System.err.println("Error al crear usuario: " + e.awsErrorDetails().errorMessage());
            e.printStackTrace();
        }
    }

    /**
     * Establece o restablece la contraseña de un usuario de forma administrativa.
     * Esto no requiere la contraseña anterior del usuario.
     */
    public static void adminSetUserPassword(CognitoIdentityProviderClient cognitoClient, String username, String newPassword, boolean permanent) {
        System.out.println("\n--- Estableciendo contraseña para: " + username + " ---");
        try {
            AdminSetUserPasswordRequest request = AdminSetUserPasswordRequest.builder()
                    .userPoolId(USER_POOL_ID)
                    .username(username)
                    .password(newPassword)
                    .permanent(permanent) // true para que el usuario no necesite cambiarla al primer login
                    .build();

            cognitoClient.adminSetUserPassword(request);
            System.out.println("Contraseña para '" + username + "' establecida exitosamente. Permanente: " + permanent);
        } catch (UserNotFoundException e) {
            System.err.println("Error: Usuario '" + username + "' no encontrado. " + e.getMessage());
        } catch (InvalidPasswordException e) {
            System.err.println("Error: Contraseña inválida. " + e.getMessage());
        } catch (CognitoIdentityProviderException e) {
            System.err.println("Error al establecer contraseña: " + e.awsErrorDetails().errorMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lista todos los usuarios en el User Pool.
     * Puede paginar los resultados si hay muchos usuarios.
     */
    public static void listAllUsers(CognitoIdentityProviderClient cognitoClient) {
        System.out.println("\n--- Listando todos los usuarios en el User Pool ---");
        ListUsersRequest request = ListUsersRequest.builder()
                .userPoolId(USER_POOL_ID)
                .limit(60) // Número máximo de usuarios a retornar por llamada (hasta 60)
                .build();

        try {
            ListUsersResponse response = cognitoClient.listUsers(request);
            if (response.hasUsers()) {
                System.out.println("Usuarios encontrados:");
                for (UserType user : response.users()) {
                    System.out.println("  Username: " + user.username() + ", Estado: " + user.userStatusAsString());
                    user.attributes().forEach(attr -> System.out.println("    " + attr.name() + ": " + attr.value()));
                    System.out.println("---");
                }
            } else {
                System.out.println("No se encontraron usuarios.");
            }

            // Si hay más usuarios, response.nextToken() contendrá el token para la siguiente página
            if (response.nextToken() != null && !response.nextToken().isEmpty()) {
                System.out.println("Hay más usuarios. Siguiente token: " + response.nextToken());
                // Podrías hacer otra llamada con request.nextToken(response.nextToken()).build()
            }

        } catch (CognitoIdentityProviderException e) {
            System.err.println("Error al listar usuarios: " + e.awsErrorDetails().errorMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene los detalles de un usuario específico.
     * Utiliza AdminGetUser, que no requiere tokens de sesión del usuario.
     */
    public static void getUserDetails(CognitoIdentityProviderClient cognitoClient, String username) {
        System.out.println("\n--- Obteniendo detalles del usuario: " + username + " ---");
        AdminGetUserRequest request = AdminGetUserRequest.builder()
                .userPoolId(USER_POOL_ID)
                .username(username)
                .build();

        try {
            AdminGetUserResponse response = cognitoClient.adminGetUser(request);
            System.out.println("Detalles para usuario '" + response.username() + "':");
            System.out.println("  Estado: " + response.userStatusAsString());
            System.out.println("  Fecha de creación: " + response.userCreateDate());
            System.out.println("  Última modificación: " + response.userLastModifiedDate());
            System.out.println("  Atributos:");
            if (response.hasUserAttributes()) {
                for (AttributeType attribute : response.userAttributes()) {
                    System.out.println("    " + attribute.name() + ": " + attribute.value());
                }
            } else {
                System.out.println("    No hay atributos adicionales.");
            }

        } catch (UserNotFoundException e) {
            System.err.println("Error: Usuario '" + username + "' no encontrado. " + e.getMessage());
        } catch (CognitoIdentityProviderException e) {
            System.err.println("Error al obtener detalles del usuario: " + e.awsErrorDetails().errorMessage());
            e.printStackTrace();
        }
    }

    public static void listAllUsersPaginated(CognitoIdentityProviderClient cognitoClient) {
        System.out.println("\n--- Listando todos los usuarios (con paginación) ---");

        String paginationToken = null; // Inicialmente no hay token
        int pageCounter = 0;

        do {
            pageCounter++;
            System.out.println("\n--- Página " + pageCounter + " ---");

            // Construir la solicitud. Si hay un token de paginación, lo añadimos.
            ListUsersRequest.Builder requestBuilder = ListUsersRequest.builder()
                    .userPoolId(USER_POOL_ID)
                    .limit(60); // Número de usuarios por página (máx. 60)

            if (paginationToken != null && !paginationToken.isEmpty()) {
                requestBuilder.paginationToken(paginationToken); // Aquí se usa el token de la respuesta anterior
            }

            ListUsersRequest request = requestBuilder.build();

            try {
                ListUsersResponse response = cognitoClient.listUsers(request);

                if (response.hasUsers()) {
                    System.out.println("Usuarios encontrados en esta página:");
                    for (UserType user : response.users()) {
                        System.out.println("  Username: " + user.username() + ", Estado: " + user.userStatusAsString());
                    }
                } else {
                    System.out.println("No se encontraron usuarios en esta página.");
                }

                // Obtener el token para la próxima página desde la RESPUESTA
                // ESTA ES LA LÍNEA CRÍTICA donde response.nextToken() es llamado:
                paginationToken = response.paginationToken(); 

            } catch (CognitoIdentityProviderException e) {
                System.err.println("Error al listar usuarios: " + e.awsErrorDetails().errorMessage());
                e.printStackTrace();
                break; // Salir del bucle en caso de error
            }

        } while (paginationToken != null && !paginationToken.isEmpty()); // Continuar si hay un token
        
        System.out.println("\n--- Fin de la lista de usuarios ---");
    }
}
