package com.api.domain.models;

import com.api.domain.models.enums.TaskCategoria;
import com.api.domain.models.enums.TaskStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Task")
public class TaskModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskCategoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
 
    


    public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TaskCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TaskCategoria categoria) {
        this.categoria = categoria;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskModel() {
    }

	public TaskModel(Long id, String titulo, String descricao, TaskCategoria categoria, TaskStatus status,
			UserModel user) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.status = status;
		this.user = user;
	}

    


}

