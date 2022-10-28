package com.example.cambak.cambak.common.util

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.cambak.cambak.common.config.s3.S3Config
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import kotlin.jvm.Throws

@Service
class AmazonS3Service {
    @Autowired
    lateinit var s3Config: S3Config

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    @Throws(Exception::class)
    fun deleteImagesS3(
        fileIdList: List<String>
    ){
        fileIdList.forEach{
            s3Config.getAmazonClient().deleteObject(bucket,it)
        }
    }

    @Throws(Exception::class)
    fun deleteImageS3(
        fildId: String
    ){
        s3Config.getAmazonClient().deleteObject(bucket, fildId)
    }

    @Throws(Exception::class)
    fun uploadImageS3(
        uploadFile: MultipartFile,
        fname: String
    ): String{
        val convertedFile: File = convert(uploadFile)
        return uploadFile(convertedFile, fname)
    }

    private fun uploadFile(
        uploadFile: File,
        fname: String
    ): String{
        val uploadImageUrl: String = putS3(uploadFile, fname)
        removeFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(
        uploadFile: File,
        fname: String
    ):String{
        val i = s3Config.getAmazonClient().putObject(
            PutObjectRequest(
                bucket,
                fname,
                uploadFile
            )
                .withCannedAcl(
                    CannedAccessControlList.PublicRead
                )
        )
        return s3Config.getAmazonClient().getUrl(bucket,fname).toString()
    }

    private fun removeFile(targetFile: File){
        targetFile.delete()
    }

    private fun convert(file: MultipartFile): File {
        val convertFile = File(System.getProperty("user.dir") + "/" + file.originalFilename)

        return if(convertFile.createNewFile()){
            val fos = FileOutputStream(convertFile)
            fos.write(file.bytes)
            convertFile
        }else{
            File("")
        }
    }
}