### Examples to run the features

- GraphQL exposes only one endpoint ```<host-name>/graphql```

### 1. Using Postman
- Request Type GraphQL (NOT HTTP)
- URL: ``` http://localhost:8080/graphql```
- In query (query messages with their users)
 ```graphql
        query {
          messages {
            id
            user {
                id
                username
            }
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

##### Test: Create User
```graphql
    query {
    users {
        id
        username
        messages {
            id
            content
            timestamp
        }
    }
}
```

##### Query Users with their messages
```graphql
    query {
    users {
        id
        username
        messages {
            id
            content
            timestamp
        }
    }
}
```
- Mutate the DB (write to the DB)
- createMessage is the "**MutationMapping**" in the code, that accepts two String args
- Create the message with payload sender and content and return the 
  four fields (id, sender, content, timestamp)
```graphql
    mutation {
        createMessage(userId: 102, content: "Hello GraphQL") {
                id
                user {
                    id
                    username
                }
                content
                timestamp
        }
    }
```

##### Get Messages with Sender (user) INFO
```graphql
query {
  messages {
    id
    content
    user {
      id
      username
    }
  }
}
```

##### Create message with sender info
```graphql
mutation {
    createMessage(content: "How to retrieve messages from queue effectively ?",
     userId: {
         username: "badripaudel"
     }) {
        id
        content
        user {
            id
            username
        }
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