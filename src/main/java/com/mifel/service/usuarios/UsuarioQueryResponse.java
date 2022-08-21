package com.mifel.service.usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuarioQueryResponse {
    List<Usuario> usuarios = new ArrayList<>();
    boolean success;
    int count;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
