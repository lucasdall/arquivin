//package test;
//
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import br.com.alo.credential.model.Permission;
//import br.com.alo.credential.model.User;
//import br.com.alo.credential.persistence.cfg.AloCredencialCfg;
//import br.com.alo.credential.persistence.repo.EntityRepository;
//import br.com.alo.credential.persistence.repo.EventRepository;
//import br.com.alo.credential.persistence.repo.PermissionRepository;
//import br.com.alo.credential.persistence.repo.UserRepository;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AloCredencialCfg.class)
//public class TestRepositories {
//
//	@BeforeClass
//	public static void setup() {
//		System.setProperty("alo.env", "D");
//	}
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private EventRepository eventRepository;
//
//	@Autowired
//	private EntityRepository partnerRepository;
//
//	@Autowired
//	private  PermissionRepository permissionRepository;
//	
//	@Test
//	public void testUser() {
//		User u = new User();
//		u.setEmail("lucas@aloingressos.com.br");
//		u.setName("Lucas");
//		u.setPwd("ZZZZZZZ");
//		
//		Permission p = new Permission();
//		p.setName("ADMIN");
//		u.getPermissions().add(p);
//		
//		u = userRepository.save(u);
//		System.out.println(String.format("user = %s", ToStringBuilder.reflectionToString(u)));
//		System.out.println(String.format("count = [%s]", userRepository.count()));
//		System.out.println("===================================");
//	}
//
//}
