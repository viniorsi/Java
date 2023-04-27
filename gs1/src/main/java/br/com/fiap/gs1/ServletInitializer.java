import org.springframework.boot.builder.SpringApplicationBuilder;

public class ServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProdutoApplication.class);
	}
	
}
