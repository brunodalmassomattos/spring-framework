package com.example.aulaspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job imprimeOlaMundoJob() {
		return this.jobBuilderFactory.get("imprimeOlaMundoJob").start(this.imprimeOlaMundoStep())
				.incrementer(new RunIdIncrementer()).build();
	}

	public Step imprimeOlaMundoStep() {
		return this.stepBuilderFactory.get("imprimeOlaMundoStep").tasklet(this.imprimeOlaMundoTasklet("")).build();
	}

	@Bean
	@StepScope
	public Tasklet imprimeOlaMundoTasklet(@Value("#{jobParameters['nome']}") String nome) {
		return new Tasklet() {
			@Override	
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println(String.format("Ola mundo - %s", nome));
				return RepeatStatus.FINISHED;
			}
		};
	}
}
