package com.codeonblue.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FileSystemStorageServiceTest {

    // TODO - Implement test for delete image
    // Todo - Implement test for insert image

    private FileSystemStorageService fileSystemStorageService;
    private Method method;
    private static String METHOD_NAME = "isValidExtension";
    private Class[] parameterTypes;
    private Object[] parameters;

    @Before
    public void setUp() throws Exception {
        fileSystemStorageService = new FileSystemStorageService();
        parameterTypes = new Class[1];
        parameterTypes[0] = MultipartFile.class;
        method = fileSystemStorageService.getClass().getDeclaredMethod(METHOD_NAME, parameterTypes);
        method.setAccessible(true);
        parameters = new Object[1];
    }

    @Test
    public void isValidExtensionMethodShouldReturnTrueToAJpegFile() throws InvocationTargetException, IllegalAccessException {
        MultipartFile multipartFile = createJPGMultipartFile();

        parameters[0] = multipartFile;
        Boolean result = (Boolean) method.invoke(fileSystemStorageService, parameters);

        Assert.assertTrue(result);
    }

    @Test
    public void isValidExtensionMethodShouldReturnFalseToANonJpegFile() throws InvocationTargetException, IllegalAccessException {
        MultipartFile multipartFile = createNonJpgOrPngMultipartFile();

        parameters[0] = multipartFile;
        Boolean result = (Boolean) method.invoke(fileSystemStorageService, parameters);

        Assert.assertFalse(result);
    }

    private MultipartFile createJPGMultipartFile() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return "teste.jpg";
            }

            @Override
            public String getOriginalFilename() {
                return "teste.jpg";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
    }

    private MultipartFile createNonJpgOrPngMultipartFile() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return "teste.gif";
            }

            @Override
            public String getOriginalFilename() {
                return "teste.gif";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
    }

}