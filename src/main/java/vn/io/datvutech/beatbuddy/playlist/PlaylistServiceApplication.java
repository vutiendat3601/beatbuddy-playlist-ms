package vn.io.datvutech.beatbuddy.playlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients
@SpringBootApplication
public class PlaylistServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaylistServiceApplication.class, args);
	}
}
