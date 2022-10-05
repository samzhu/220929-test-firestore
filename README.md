# Cloud run with firestore


## Service Account

``` bash
export PROJECT_ID=das-ct-lab
export SERVICE_ACCOUNT_ID_RUNTIME=sa-cloudrun-firestore

gcloud iam service-accounts create $SERVICE_ACCOUNT_ID_RUNTIME \
    --description="To cloud run runtime use firestore" \
    --display-name="cloud run sa firestore" \
    --project=$PROJECT_ID
```

角色綁定

``` bash
gcloud projects add-iam-policy-binding $PROJECT_ID \
    --member="serviceAccount:$SERVICE_ACCOUNT_ID_RUNTIME@$PROJECT_ID.iam.gserviceaccount.com" \
    --role="roles/datastore.user"
gcloud projects add-iam-policy-binding $PROJECT_ID \
    --member="serviceAccount:$SERVICE_ACCOUNT_ID_RUNTIME@$PROJECT_ID.iam.gserviceaccount.com" \
    --role="roles/cloudtrace.agent"
gcloud projects add-iam-policy-binding $PROJECT_ID \
    --member="serviceAccount:$SERVICE_ACCOUNT_ID_RUNTIME@$PROJECT_ID.iam.gserviceaccount.com" \
    --role="roles/logging.serviceAgent"
```


https://googlecloudplatform.github.io/spring-cloud-gcp/3.3.0/reference/html/index.html#spring-data-cloud-firestore

https://codelabs.developers.google.com/codelabs/cloud-spring-datastore?hl=ko&continue=https%3A%2F%2Fcodelabs.developers.google.com%2Fspring%2F%3Fhl%3DPT#6
