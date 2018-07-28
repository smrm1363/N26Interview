This application is a simple way to have a transaction statistics collector. It have two webservices :
POST /transactions : inserts each transaction data in memory.
GET /statistics : returns statis of transactions which are in the last 60 seconds. Its complexity is O(1).
This appllication developed by Spring boot and has below elements:
 1- The API is threadsafe with concurrent requests
 2- The API functions properly, with proper result
 3- The project is buildable, and tests should also complete successfully.
 4- The API is able to deal with time discrepancy, which means, at any point of time,
    we could receive a transaction which have a timestamp of the past
 5- sends the case in memory solution without database (including in-memory database)
 6- Endpoints are execute in constant time and memory (O(1))
 7- completed the challenge using Java 8
