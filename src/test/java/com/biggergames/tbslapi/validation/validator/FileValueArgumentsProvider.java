package com.biggergames.tbslapi.validation.validator;


import com.biggergames.tbslapi.validation.constraints.FileValueSource;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileValueArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<FileValueSource> {

    private Object[] arguments;

    public void accept(FileValueSource source) {
        List<String> files = Stream.of(source.files()).collect(Collectors.toList());
        if (files.isEmpty()) {
            try {
                this.arguments = getResourceFiles("request/" + source.root() + "/").stream().filter(p -> p.contains(".json")).map(p -> {
                    try {
                        return new String(new ClassPathResource("request/" + source.root() + "/" + p).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).toArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.arguments = files.stream().map(p -> {
                try {
                    return new String(new ClassPathResource("request/" + source.root() + "/" + p + ".json").getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }).toArray();
        }
    }

    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Arrays.stream(this.arguments).map((p) -> Arguments.of(p));
    }

    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (InputStream in = getResourceAsStream(path); BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}



