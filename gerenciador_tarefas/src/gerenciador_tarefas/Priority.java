package gerenciador_tarefas;

public enum Priority {
    HIGH("Alta"),
    MEDIUM("Média"),
    LOW("Baixa");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
