package com.example.testfirestore;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import com.google.cloud.spring.data.firestore.Document;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collectionName = "usersCollection")
public class User {
  

  @DocumentId
  String name;
 /** com.google.cloud.firestore.annotation.PropertyName 必須是 public 才有作用. */
  String favoriteDrink;

  Integer age;

}