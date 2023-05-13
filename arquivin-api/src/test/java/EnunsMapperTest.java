//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import org.apache.commons.lang3.time.StopWatch;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.arquivin.api.cfg.ArquivinRestCfg;
//import br.com.arquivin.model.Company;
//import br.com.arquivin.model.enuns.CompanyMainColor;
//import br.com.arquivin.model.enuns.HeaderColor;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = ArquivinRestCfg.class)
//@WebAppConfiguration
//// @Transactional
//// @Rollback(false)
//public class EnunsMapperTest {
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@BeforeClass
//	public static void setup() {
//		System.setProperty("arquivin.env", "D");
//		System.setProperty("arquivin.tokenExpiration", "6000000");
//		System.setProperty("arquivin.secret", "secret");
//	}
//
//	@Test
//	public void testEnuns() {
//		try {
//			StopWatch sw = new StopWatch();
//			sw.start();
//			Company c = new Company();
//			c.setIdCompany(1l);
//			c.setName("Teste");
//			c.setCnpj("99999999999999");
//			c.setSubDomain("andes");
//			c.setHeaderColor(HeaderColor.BLACK);
//			c.setMainColor(CompanyMainColor.RED);
//			String json = objectMapper.writeValueAsString(c);
//			System.out.println(json);
//			
//			Company c2 = objectMapper.readValue(json, Company.class);
//			System.out.println(ToStringBuilder.reflectionToString(c2, ToStringStyle.MULTI_LINE_STYLE));
//			sw.stop();
//			System.out.println(sw);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
