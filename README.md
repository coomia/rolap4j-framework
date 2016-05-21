# rolap4j-framework

Framework for Business Intelligence based on Relational OnLine Analytical Processing Architecture.

Rolap4j is still under dev ...

Steps to use Rolap4j
--------------------
The following steps show how to use the Rolap4j Framework (In the `rolap4j-framework-demo-resources`, you can see some useful resources).

1. Create a relational based data warehouse
2. Create a catalog file that describes the data warehouse
3. Create a configuration file defining all parameters

Step 1 - Relational based data warehouse
----------------------------------------

In ROLAP architecture, the data warehouse consists of several data marts. Each data mart often consists of one or more fact tables (measurements, metrics, ...) referencing any number of dimension tables (Used for projection).
In computing, this representation is called 'Star schema'. 

![Image not found](https://raw.githubusercontent.com/Andriantomanga/rolap4j-framework/master/rolap4j-star-schema.png "Star schema")

Step 2 - Catalog
----------------

The catalog is an XML file that describes the data warehouse. Its structure is based on
[Mondrian scheme]( http://mondrian.pentaho.com/documentation/schema.php) with some restrictions.

Here is the basic structure of the catalog (Get inspired from the demo to help you):

```xml
<Schema>
    <Dimension ...>
        <Hierarchy ...>
            <Table ... />
            <Level ... />
            ...
        </Hierarchy>
    </Dimension>
    ...
    <Cube ...>
        <Table ... />
        <DimensionUsage ... />
        ...
        <Measure ... />
    </Cube>
    ...
</Schema>
```

Step 3 - Configuration
----------------------

Define the following environment variable ```ROLAP4J_HOME``` which is the location where Rolap4j will 
search a file named ```datawarehouse.properties``` in which you put the following properties:

- uri : URI for connecting to the database. For example : jdbc\:mysql\://localhost:3306/foodMart
- username : username for connecting to the database.
- password : password for connecting to the database.
- catalog : path to the catalog. You also can use environment variable here if you want. For example : ${ROLAP4J_HOME}/foodMart.xml
- show_mdx : Set ```true``` if you want to show the corresponding MDX expression to your query


Example
-------

The following example introduces the usage of Rolap4j Framework.
```java
 try {
            // Building the query ...
            Query query = new Query.QueryBuilder().fromCube("Sales")
                    .useColumn("City").useColumn("Town")
                    .useRow("Provider")
                    .userFilter("Quantity")
                    .build();

            // Executing the query ...
            CellSet results = query.executeQuery();
            
            // Use the results ...
            
        } catch (Rolap4jException e) {
            e.printStackTrace();
        }
```

You also can directly use MDX query 
```java
try {
            
            // Creating a MDX query
            StringBuilder mdx = new StringBuilder();

            mdx.append("SELECT");
            mdx.append("{Hierarchize({[Product].[Macbook Pro]})} ON COLUMNS, ");
            mdx.append("{Hierarchize({[Location].[Paris]})} ON ROWS ");
            mdx.append("FROM [Sales] ");

            // Setting the query ...
            Query query = new Query.QueryBuilder().setMdx(mdx.toString()).build();
            
            // Just printing the results ...
            query.printResults();
            
            // Retrieving the results in iterable object ...
            CellSet results = query.executeQuery();

        } catch (Rolap4jException e) {
            e.printStackTrace();
        }
```

Building the framework
----------------------

```mvn clean install -DskipTests```










