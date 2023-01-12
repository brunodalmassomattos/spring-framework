package med.voll.api.dao;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import med.voll.api.records.medicos.DadosCadastroMedico;
import med.voll.api.records.medicos.Especialidade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicos")
@Entity(name = "Medico")
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;

	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	public Medico(DadosCadastroMedico dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.crm = dados.crm();
		this.telefone = dados.telefone();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.dadosEndereco());
	}
}
