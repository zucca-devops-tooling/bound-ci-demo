package dev.zucca_ops;
import dev.zucca_ops.api.HelloApi;

public class ServiceImpl implements HelloApi {
    @Override
    public String hello() {
        return "Hello from ServiceImpl!";
    }
}