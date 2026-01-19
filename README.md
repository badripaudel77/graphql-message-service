### Examples to run the features

- GraphQL exposes only one endpoint ```<host-name>/graphql```

### 1. Using Postman
- Request Type GraphQL (NOT HTTP)
- URL: ``` http://localhost:8080/graphql```
- In query:
 ```graphql
        query {
          messages {
            id
            sender
            timestamp
            content
          }
        }
```

### 2. Testing Using graphiql UI:

- Since 'spring.graphql.graphiql.enabled=true' in the properties file,
  we get the UI for testing at : ```http://localhost:8080/graphiql```
  which allows the following queries to be passed.

##### Test Write to the database [Create Message - Mutation]

- Mutate the DB (write to the DB)
- createMessage is the "**MutationMapping**" in the code, that accepts two String args
- Create the message with payload sender and content and return the 
  four fields (id, sender, content, timestamp)
```graphql
    mutation {
        createMessage(sender: "Alice", content: "Hello GraphQL") {
                id
                sender
                content
                timestamp
        }
    }
```

##### Test: Get All Messages (Query)
- Get all messages query (Annotated with "**@QueryMapping**" in the code) with method name "**messages**"
- Takes no args
```graphql
    query {
        messages {
            id
            sender
            content
            timestamp
        }
    }
```

##### Test: Get Message By Id (Annotated with "@**QueryMapping**") - method name "**messageById**" that accepts 1 id arg
- Test ID : 1
- Field can be removed if not needed.
- Takes one arg "id" and returns the four fields (id, sender, content, timestamp)
- We can always remove a field from the query without modifying the API code.
```graphql
    query {
        messageById(id: 1) {
            id
            sender
            content
            timestamp
        }
    }
```