package com.ejemplo.dynamoDB;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.util.HashMap;
import java.util.Map;

public class DynamoDBTableAndDataExample {
    /**
     * Este ejemplo muestra cómo crear una tabla en DynamoDB y agregar algunos ítems de ejemplo.
     * Asegúrate de tener las credenciales de AWS configuradas correctamente.
     */

    private static final String TABLE_NAME = "Products";
    private static final String PARTITION_KEY = "ProductId";
    private static final String SORT_KEY = "Category"; // Opcional: si quieres una clave de ordenación

    public static void main(String[] args) {
        // 1. Configurar el cliente de DynamoDB
        // El SDK buscará las credenciales y la región automáticamente
        // (ej. de variables de entorno, archivo ~/.aws/credentials, etc.)
        DynamoDbClient ddbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Reemplaza con tu región de AWS
                .build();

        // 2. Crear la tabla
        createTable(ddbClient, TABLE_NAME, PARTITION_KEY, SORT_KEY);

        // 3. Agregar datos a la tabla
        addItemsToTable(ddbClient, TABLE_NAME, PARTITION_KEY, SORT_KEY);

        // 4. Cerrar el cliente
        ddbClient.close();
    }

    /**
     * Crea una tabla en DynamoDB.
     * @param ddbClient El cliente de DynamoDB.
     * @param tableName El nombre de la tabla a crear.
     * @param partitionKey La clave de partición.
     * @param sortKey La clave de ordenación (puede ser null si no se usa).
     */
    public static void createTable(DynamoDbClient ddbClient, String tableName, String partitionKey, String sortKey) {
        System.out.println("Intentando crear la tabla: " + tableName);

        try {
            // Definir los atributos clave
            AttributeDefinition partitionKeyAttribute = AttributeDefinition.builder()
                    .attributeName(partitionKey)
                    .attributeType(ScalarAttributeType.S) // S=String, N=Number, B=Binary
                    .build();

            KeySchemaElement partitionKeySchema = KeySchemaElement.builder()
                    .attributeName(partitionKey)
                    .keyType(KeyType.HASH) // HASH para clave de partición
                    .build();

            CreateTableRequest.Builder createTableRequestBuilder = CreateTableRequest.builder()
                    .tableName(tableName)
                    .attributeDefinitions(partitionKeyAttribute)
                    .keySchema(partitionKeySchema)
                    // Configura el modo de capacidad. ON_DEMAND es más simple para empezar.
                    .billingMode(BillingMode.PAY_PER_REQUEST); // O ProvisionedThroughput

            // Si tienes una clave de ordenación (Sort Key)
            if (sortKey != null && !sortKey.isEmpty()) {
                AttributeDefinition sortKeyAttribute = AttributeDefinition.builder()
                        .attributeName(sortKey)
                        .attributeType(ScalarAttributeType.S) // O el tipo de tu clave de ordenación
                        .build();
                KeySchemaElement sortKeySchema = KeySchemaElement.builder()
                        .attributeName(sortKey)
                        .keyType(KeyType.RANGE) // RANGE para clave de ordenación
                        .build();

                createTableRequestBuilder.attributeDefinitions(partitionKeyAttribute, sortKeyAttribute);
                createTableRequestBuilder.keySchema(partitionKeySchema, sortKeySchema);
            }

            CreateTableRequest createTableRequest = createTableRequestBuilder.build();

            // Enviar la solicitud para crear la tabla
            CreateTableResponse createTableResponse = ddbClient.createTable(createTableRequest);
            System.out.println("Solicitud de creación de tabla enviada. ID de la tabla: " + createTableResponse.tableDescription().tableId());

            // Esperar a que la tabla esté activa
            System.out.println("Esperando a que la tabla esté activa...");
            DynamoDbWaiter waiter = ddbClient.waiter();
            waiter.waitUntilTableExists(DescribeTableRequest.builder().tableName(tableName).build());
            System.out.println("¡Tabla '" + tableName + "' creada y activa!");

        } catch (ResourceInUseException e) {
            System.out.println("La tabla '" + tableName + "' ya existe.");
        } catch (DynamoDbException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    /**
     * Agrega ítems de ejemplo a la tabla.
     * @param ddbClient El cliente de DynamoDB.
     * @param tableName El nombre de la tabla.
     * @param partitionKey La clave de partición de la tabla.
     * @param sortKey La clave de ordenación de la tabla (puede ser null).
     */
    public static void addItemsToTable(DynamoDbClient ddbClient, String tableName, String partitionKey, String sortKey) {
        System.out.println("\nAgregando ítems a la tabla: " + tableName);

        try {
            // Ítem 1
            Map<String, AttributeValue> item1 = new HashMap<>();
            item1.put(partitionKey, AttributeValue.builder().s("P001").build());
            if (sortKey != null && !sortKey.isEmpty()) {
                item1.put(sortKey, AttributeValue.builder().s("Electronics").build());
            }
            item1.put("ProductName", AttributeValue.builder().s("Laptop X1").build());
            item1.put("Price", AttributeValue.builder().n("1200.00").build());
            item1.put("Available", AttributeValue.builder().bool(true).build());
            item1.put("Tags", AttributeValue.builder().ss("electronics", "computers", "portable").build()); // Conjunto de Strings

            PutItemRequest putItemRequest1 = PutItemRequest.builder()
                    .tableName(tableName)
                    .item(item1)
                    .build();
            ddbClient.putItem(putItemRequest1);
            System.out.println("Ítem 'P001' agregado.");

            // Ítem 2
            Map<String, AttributeValue> item2 = new HashMap<>();
            item2.put(partitionKey, AttributeValue.builder().s("P002").build());
            if (sortKey != null && !sortKey.isEmpty()) {
                item2.put(sortKey, AttributeValue.builder().s("Books").build());
            }
            item2.put("ProductName", AttributeValue.builder().s("Java Programming Guide").build());
            item2.put("Price", AttributeValue.builder().n("45.50").build());
            item2.put("Available", AttributeValue.builder().bool(true).build());
            item2.put("Pages", AttributeValue.builder().n("800").build());
            item2.put("Authors", AttributeValue.builder().l( // Lista de atributos
                    AttributeValue.builder().s("John Doe").build(),
                    AttributeValue.builder().s("Jane Smith").build()
            ).build());

            PutItemRequest putItemRequest2 = PutItemRequest.builder()
                    .tableName(tableName)
                    .item(item2)
                    .build();
            ddbClient.putItem(putItemRequest2);
            System.out.println("Ítem 'P002' agregado.");

            // Ítem 3 (mismo Partition Key, diferente Sort Key si existe)
            if (sortKey != null && !sortKey.isEmpty()) {
                Map<String, AttributeValue> item3 = new HashMap<>();
                item3.put(partitionKey, AttributeValue.builder().s("P001").build()); // Mismo ProductId
                item3.put(sortKey, AttributeValue.builder().s("Accessories").build()); // Diferente Category
                item3.put("ProductName", AttributeValue.builder().s("Wireless Mouse").build());
                item3.put("Price", AttributeValue.builder().n("25.00").build());
                item3.put("Available", AttributeValue.builder().bool(true).build());

                PutItemRequest putItemRequest3 = PutItemRequest.builder()
                        .tableName(tableName)
                        .item(item3)
                        .build();
                ddbClient.putItem(putItemRequest3);
                System.out.println("Ítem 'P001' (Accessories) agregado.");
            }

        } catch (DynamoDbException e) {
            System.err.println("Error al agregar ítems a la tabla: " + e.getMessage());
        }
    }
}
