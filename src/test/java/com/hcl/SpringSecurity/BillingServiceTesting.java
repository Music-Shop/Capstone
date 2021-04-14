//package com.hcl.SpringSecurity;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Date;
//
//import com.hcl.MusicMelody.models.BillingInformation;
//import com.hcl.MusicMelody.repositories.BillingInformationRepository;
//import com.hcl.MusicMelody.services.BillingInformationService;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.BootstrapWith;
//
//
//// @RunWith(SpringJUnit4ClassRunner.class)
//@BootstrapWith(value=org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTestContextBootstrapper)
//@SpringBootTest
//@DataJpaTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class BillingServiceTesting {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Mock
//    private BillingInformationService billingService;
//
//    @Mock
//    private BillingInformationRepository mockBillingRepo;
//
//
//    private BillingInformation testBilling;
//
//    @BeforeEach
//    public void setup() {
//        testBilling = new BillingInformation(1,"123 Street Ave.", "", "San Antonio", "TX", "12345", "567", new Date(), "1111222233334444");
//        entityManager.persist(testBilling);
//        entityManager.flush();
//    }
//
//    @Test
//    public void shouldGetBillingInfoById() {
//        // find case
//        BillingInformation billing = billingService.getBillingInformationById(1).get();
//        assertTrue(billing.equals(testBilling));
//
//        // not found case
//        billing = billingService.getBillingInformationById(2).get();
//        assertFalse(billing.equals(testBilling));
//    }
//
//    @Test
//    public void shouldSaveOrUpdate() {
//        assertTrue(true);
//    }
//}
