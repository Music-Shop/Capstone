package com.hcl.SpringSecurity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.hcl.MusicMelody.models.BillingInformation;
import com.hcl.MusicMelody.repositories.BillingInformationRepository;
import com.hcl.MusicMelody.services.BillingInformationService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

// @RunWith(SpringRunner.class)
// @DataJpaTest
// @SpringBootTest(classes = {BillingInformationService.class, BillingInformationRepository.class, BillingInformation.class})
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// @BootstrapWith(TestContextBootstrapper.class)
public class BillingServiceTesting {
    
    @InjectMocks
    private BillingInformationService billingService;

    @Mock
    private BillingInformationRepository billingRepo;

    private BillingInformation testBilling;


    @BeforeEach
    public void init() {
        testBilling = new BillingInformation(1, "123 Street Ave.", "", "San Antonio", "TX", "12345", "345", new Date(), "1111222233334444");
        when(billingRepo.save(any(BillingInformation.class))).then();
    }

    @Test
    public void shouldGetBillingInfoById() {
        // found case
        Optional<BillingInformation> billing = billingService.getBillingInformationById(1);
        assertTrue(billing.isPresent());
        assertTrue(billing.get().equals(testBilling));

        //not found case
        billing = billingService.getBillingInformationById(2);
        assertFalse(billing.isPresent());
        assertFalse(billing.get().equals(testBilling));
    }

    @Test
    public void shouldSaveAndUpdate() {
        // billingService.saveOrUpdate(testBilling);
    }
}
