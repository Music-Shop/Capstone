package com.hcl.SpringSecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.amazonaws.services.directconnect.model.NewPublicVirtualInterface;
import com.hcl.MusicMelody.models.BillingInformation;
import com.hcl.MusicMelody.repositories.BillingInformationRepository;
import com.hcl.MusicMelody.services.BillingInformationService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private ArrayList<BillingInformation> billingInfo;


    @BeforeAll
    public void init() {
        billingInfo = new ArrayList<>();
        testBilling = new BillingInformation(1, "123 Street Ave.", "", "San Antonio", "TX", "12345", "345", new Date(), "1111222233334444");
        billingInfo.add(testBilling);
        
    }

    @Test
    public void shouldGetBillingInfoById() {
        // found case
        when(billingRepo.findById(1)).thenReturn(Optional.ofNullable(billingInfo.get(0)));
        BillingInformation billing = billingService.getBillingInformationById(1);
        assertEquals(billing, testBilling);

        // not found case  
        when(billingRepo.findById(2)).thenReturn(Optional.ofNullable(null));
        assertNull(billingService.getBillingInformationById(2));
    }
}
