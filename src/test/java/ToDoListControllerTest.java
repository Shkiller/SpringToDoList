import main.model.Case;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ToDoListControllerTest extends AbstractIntegrationTest {
    @Before
    public void setUp() {
        Case newCase1 = new Case();
        newCase1.setCaseString("111");
        toDoListRepository.save(newCase1);
        Case newCase2 = new Case();
        newCase2.setCaseString("222");
        toDoListRepository.save(newCase2);
        Case newCase3 = new Case();
        newCase3.setCaseString("333");
        toDoListRepository.save(newCase3);
    }

    @Test
    public void TestAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/cases/").param("caseString", "123"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseString").value("123"));
    }

    @Test
    public void TestList() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cases/"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void TestGet() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/cases/{id}", "2"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseString").value("222"));

    }

    @Test
    public void TestUpdateCase() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/cases/{id}","1").param("caseString", "000"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caseString").value("000"));;
    }

    @Test
    public void TestRemove() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/cases/{id}","3"))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }


}
