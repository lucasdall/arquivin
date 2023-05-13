//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import br.com.arquivin.api.cfg.ArquivinRestCfg;
//
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = ArquivinRestCfg.class)
//@WebAppConfiguration
////@Transactional
////@Rollback(false)
//public class ModelMapperTest {
//	
////	@Autowired
////	private ProjectService projectService;
////	
////	@Autowired
////	private UserService userService;
//	
////	@Autowired
////	private ModelMapper modelMapper;
////	
////	@Autowired
////	private ObjectMapper objectMapper;
//	
//	@BeforeClass
//	public static void setup() {
//		System.setProperty("arquivin.env", "D");
//		System.setProperty("arquivin.tokenExpiration", "6000000");
//		System.setProperty("arquivin.secret", "secret");
//	}
//	
//	@Test
//	public void testCycle() {
////		List<Project> models = projectService.findAll(Sort.by(Order.desc("idProject")));
////		List<ProjectDTO> dto = new ArrayList<>(0);
////		StopWatch sw = new StopWatch();
////		sw.start();
////		List<ProjectDTO> dto = models.stream().map(p -> modelMapper.map(p, ProjectDTO.class)).collect(Collectors.toList());
////		List<ProjectDTO> dto = models.stream().map(p -> objectMapper.convertValue(p, ProjectDTO.class)).collect(Collectors.toList());
////		User u = userService.findById(1l);
////		List<ProjectListViewDTO> dto = projectService.findAllByUser(u);
////		sw.stop();
////		System.out.println(sw);
////		List<ProjectDTO> dto = modelMapper.map(models, new TypeToken<List<ProjectDTO>>() {}.getType());
////		dto = modelMapper.map(models, new TypeToken<List<ProjectDTO>>() {}.getType());
////		dto.stream().forEach(d -> System.out.println(ToStringBuilder.reflectionToString(d, org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE)));
//	}
//
//}
