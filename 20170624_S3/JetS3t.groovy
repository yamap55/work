@Grab("net.java.dev.jets3t:jets3t:0.9.4")

import org.jets3t.service.impl.rest.httpclient.RestS3Service
import org.jets3t.service.model.S3Bucket
import org.jets3t.service.model.S3Object
import org.jets3t.service.security.AWSCredentials

// https://stackoverflow.com/questions/22284699/file-upload-to-amazon-s3-via-groovy

accessKey = Define.ACCESS_KEY_ID
secretKey = Define.SECRET_KEY
bucketName = "yamap55.test.bucket"
fileName = "upload.txt"

credentials = new AWSCredentials(accessKey, secretKey)
service = new RestS3Service(credentials)
bucket = new S3Bucket(bucketName)
file = new File(fileName)
fileObject = new S3Object(file)
fileObject.key = fileName
service.putObject(bucket, fileObject)
expiryTime = new Date() + 1 // 24 hours from current date
link = service.createSignedGetUrl(bucket.name, fileObject.key, expiryTime)
println "$fileName : $link"
