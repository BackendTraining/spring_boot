package com.training.springbootbuyitem.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import zipkin2.Call;
import zipkin2.Span;
import zipkin2.codec.Encoding;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;

import java.util.List;

@Slf4j
@Configuration
@ComponentScan({"com.training.springbootbuyitem"})
public class TraceIdConfiguration {

    @Bean(ZipkinAutoConfiguration.REPORTER_BEAN_NAME)
    Reporter<Span> itemStorageReporter() {
        return AsyncReporter.create(itemStorageSender());
    }

    @Bean(ZipkinAutoConfiguration.SENDER_BEAN_NAME)
    InternalSender itemStorageSender() {
        return new InternalSender();
    }

    static class InternalSender extends Sender {

        private boolean spanSent = false;

        boolean isSpanSent() {
            return this.spanSent;
        }

        @Override
        public Encoding encoding() {
            return Encoding.JSON;
        }

        @Override
        public int messageMaxBytes() {
            return Integer.MAX_VALUE;
        }

        @Override
        public int messageSizeInBytes(List<byte[]> encodedSpans) {
            return encoding().listSizeInBytes(encodedSpans);
        }

        @Override
        public Call<Void> sendSpans(List<byte[]> encodedSpans) {
            this.spanSent = true;
            return Call.create(null);
        }

    }

}

