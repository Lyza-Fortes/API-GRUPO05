package br.com.api.g5.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.g5.dto.UserDTO;
import br.com.api.g5.entities.User;

@Configuration
@Service
public class EmailService {

	@Autowired
	UserService userService;

	private JavaMailSender emailSender;

	// no email o @Autowired é feito no construtor por causa da dependência
	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		emailSender.setHost(host);
		emailSender.setPort(port);
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		emailSender.setJavaMailProperties(prop);
		return emailSender;
	}

	public void envioEmailCadastro(UserDTO user) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo5api20232@gmail.com");
			helper.setTo("arthurmonteiro20172018@gmail.com");
			helper.setSubject("Cadastro concluido!");

			LocalDate localDate = LocalDate.now();

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<img src=\"cid:logo1\">\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>Confirmação de Cadastro</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Olá,\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Agradecemos por escolher a GRUPO 5 ENTERPRISE e se cadastrar em nossa plataforma! Estamos felizes em tê-lo a bordo. :D\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Aqui estão os detalhes do seu cadastro:\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Nome de Usuário: \r\n");
			builder.append(user.getNomeUsuario());
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Endereço de E-mail: \r\n");
			builder.append(user.getEmail());
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Data de Cadastro: \r\n");
			builder.append(localDate);
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Você terá acesso a um mundo de recursos e oportunidades. Explore nossa plataforma para descobrir como podemos ajudá-lo a atingir seus objetivos.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Se você tiver alguma dúvida ou precisar de assistência, não hesite em entrar em contato conosco pelo e-mail: grupo5api20232@gmail.com. Nossa equipe de suporte estará pronta para ajudar.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Atenciosamente,\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			A Equipe da GRUPO 5 ENTERPRISE.\r\n");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			ClassPathResource img = new ClassPathResource("img/logo_grupo_5.png");
			helper.addInline("logo1", img);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void envioEmailEncerramentoConta(User user) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo5api20232@gmail.com");
			helper.setTo("arthurmonteiro20172018@gmail.com");
			helper.setSubject("Encerramento de Conta!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("		<img src=\"cid:logo2\">\r\n");
			builder.append("			<h1>Confirmação de Encerramento de Conta</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Olá \r\n");
			builder.append(user.getNomeUsuario());
			builder.append(",");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			É com pesar que recebemos a notícia da sua decisão de encerrar sua conta na GRUPO 5 ENTERPRISE. 😔\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Queremos confirmar que sua conta associada ao endereço de e-mail: ");
			builder.append(user.getEmail());
			builder.append(" foi removida com sucesso do nosso sistema.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Entendemos que as razões para encerrar uma conta podem variar, e respeitamos a sua decisão. Se em algum momento você reconsiderar ou se precisar de informações adicionais sobre sua conta ou qualquer outro assunto, não hesite em entrar em contato conosco pelo e-mail: grupo5api20232@gmail.com.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Lamentamos vê-lo partir e agradecemos por ter sido parte da nossa comunidade. Se você decidir voltar no futuro, estaremos aqui para recebê-lo de braços abertos.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Agradecemos pela oportunidade de tê-lo servido e desejamos a você sucesso em suas futuras empreitadas.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Atenciosamente,\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			A Equipe da GRUPO 5 ENTERPRISE\r\n");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			ClassPathResource img = new ClassPathResource("img/logo_grupo_5.png");
			helper.addInline("logo2", img);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	//NAO FOI TESTADO
	public void envioEmailCancelamentoPedido() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("grupo5api20232@gmail.com");
			helper.setTo("arthurmonteiro20172018@gmail.com");
			helper.setSubject("Cancelamento Pedido!");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<img src=\"cid:logo3\">\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>Confirmação de Cancelamento de Pedido</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Olá {nome_cliente},\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Lamentamos receber a notícia de que você decidiu cancelar o seu pedido. 😔\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Este e-mail é para confirmar o cancelamento do seu pedido com os seguintes detalhes:\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Número do Pedido: {numero_pedido}\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Data do Pedido: {data_pedido}\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Produtos/Serviços Cancelados: {produtos_cancelados}\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			Total do Pedido: {total_pedido}\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Entendemos que as circunstâncias podem levar a mudanças nos planos e respeitamos a sua decisão. Se você tiver algum feedback sobre a experiência ou se precisar de assistência, não hesite em entrar em contato conosco. Nossa equipe de suporte está disponível para ajudá-lo.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append(
					"			Lamentamos qualquer inconveniente que isso possa ter causado e agradecemos por considerar a GRUPO 5 ENTERPRISE. Estamos à disposição para futuras oportunidades de atendê-lo.\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			<br/>\r\n");
			builder.append("			Atenciosamente,\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"left\">\r\n");
			builder.append("			A Equipe da GRUPO 5 ENTERPRISE\r\n");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			ClassPathResource img = new ClassPathResource("img/logo_grupo_5.png");
			helper.addInline("logo3", img);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}