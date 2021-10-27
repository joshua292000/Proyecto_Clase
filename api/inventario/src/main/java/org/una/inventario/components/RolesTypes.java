package org.una.inventario.components;

public enum RolesTypes {
    ROLE_CONTADOR("CONTADOR"),
    ROLE_COLABORADOR("COLABORADOR"),
    ROLE_AUDITOR("AUDITOR"),
    ROLE_ADMINISTRADOR("ADMINISTRADOR"),
    ROLE_USUARIO("USUARIO");
    private final String codigo;

    RolesTypes(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
