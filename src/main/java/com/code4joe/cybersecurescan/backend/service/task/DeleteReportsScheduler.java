package com.code4joe.cybersecurescan.backend.service.task;


import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteReportsScheduler {

    private final MinioClient minioClient;

    Logger log = LoggerFactory.getLogger(DeleteReportsScheduler.class);

    @Scheduled(cron = "0 */2 * * * *")
    private void deleteReportsTask() {
        log.info("deleting reports from bucket ...");

        //TODO: need to create DB schema and impl so app can keep track of creation date

    }

    private void deleteObjectsFromBucket(String bucketName) {
        //TODO: need to create DB schema and impl so app can keep track of creation date
    }
}
