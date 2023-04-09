package com.fs.middlelink.medication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.middlelink.medication.dtos.CreateMedicationDto;
import com.fs.middlelink.medication.services.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest {
    @MockBean
    private MedicationService medicationService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenCreatMedicationEndpointCalled_Returns200AndBody_Test() throws Exception {
        Byte[] imageBtye = {4,7,3,5,78,1,9,0};
        CreateMedicationDto createMedicationRequest = CreateMedicationDto.builder()
                .medicationName("Novagin")
                .weight(106.41)
                .medicationPicture(imageBtye)
                .build();
        CreateMedicationDto expectedBody = CreateMedicationDto.builder()
                .medicationId("med-id-01")
                .medicationName("Novagin")
                .weight(106.41)
                .medicationPicture(imageBtye)
                .build();
        when(medicationService.createMedication(any(CreateMedicationDto.class))).thenReturn(expectedBody);

        MvcResult result = this.mockMvc.perform(post("/api/v1/medication/create-medication")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createMedicationRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        String actualBody = result.getResponse().getContentAsString();
        assertAll("action",
                ()-> verify(medicationService, times(1)).createMedication(any(CreateMedicationDto.class)),
                ()-> assertThat(actualBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expectedBody))
        );
    }
}