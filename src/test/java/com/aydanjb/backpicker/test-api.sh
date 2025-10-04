#!/bin/bash

# Register a new user
echo "Registering a new user..."
curl -s -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
  "username": "testuser",
  "email": "test@gmail.com",
  "password": "password"
  }' | jq .

# Login and get token
TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "test@gmail.com", "password": "password"}' \
  | jq -r '.token')

echo "Token: $TOKEN"
echo -e "\n"

# Create a new list and get its ID
echo "Creating a new gear list..."
LIST_ID=$(curl -s -X POST http://localhost:8080/api/lists \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "TEST LIST", "description": "Gear list for API testing!"}' | jq -r '.id')

echo "Created list with ID: $LIST_ID"
echo -e "\n"

# Add an item to the list and get its ID
echo "Adding an item to the gear list..."
ITEM_ID=$(curl -s -X POST http://localhost:8080/api/lists/"$LIST_ID"/items \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "TEST ITEM", "price": 3.50, "weight": 2.5, "quantity": 1, "category": "TEST CATEGORY"}' | jq -r .'id')

echo "Added item with ID: $ITEM_ID"
echo -e "\n"

# Get lists
echo "Fetching gear lists..."
curl -s -X GET http://localhost:8080/api/lists \
  -H "Authorization: Bearer $TOKEN" | jq .

echo -e "\n"

# Get items
echo "Fetching gear items..."
curl -s -X GET http://localhost:8080/api/lists/"$ITEM_ID"/items \
  -H "Authorization: Bearer $TOKEN" | jq .

echo -e "\n"


# Update the list
echo "Updating the gear list..."
curl -s -X PUT http://localhost:8080/api/lists/"$LIST_ID" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "UPDATED TEST LIST", "description": "Updated gear list for API testing!"}' | jq .

echo -e "\n"

# Update the item
echo "Updating the gear item..."
curl -s -X PUT http://localhost:8080/api/items/"$ITEM_ID" \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name": "UPDATED TEST ITEM", "price": 4.50}' | jq .

echo -e "\n"

# Delete the item
echo "Deleting the gear item..."
curl -s -X DELETE http://localhost:8080/api/items/"$ITEM_ID" \
  -H "Authorization: Bearer $TOKEN"

curl -s -X GET http://localhost:8080/api/lists/"$LIST_ID"/items \
  -H "Authorization: Bearer $TOKEN" | jq .

echo -e "\n"

# Delete the list
echo "Deleting the gear list..."
curl -s -X DELETE http://localhost:8080/api/lists/"$LIST_ID" \
  -H "Authorization: Bearer $TOKEN"

curl -s -X GET http://localhost:8080/api/lists \
  -H "Authorization: Bearer $TOKEN" | jq .