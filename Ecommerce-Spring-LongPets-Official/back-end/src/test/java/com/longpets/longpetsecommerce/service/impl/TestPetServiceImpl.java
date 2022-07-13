package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.response.FindPetIdResponseDto;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class TestPetServiceImpl {
    @Test
    public void addNewPet_When_HappyCase() {
        PetRepository petRepositoryMock = mock(PetRepository.class);
        NewPetRequestDto newPetRequestDtoMock = mock(NewPetRequestDto.class);
        FindPetIdResponseDto findPetIdResponseDtoMock = mock(FindPetIdResponseDto.class);
        List<String> petImageMock = mock(List.class);

        verify(petRepositoryMock).addPet(
                newPetRequestDtoMock.getCategoryId(),
                newPetRequestDtoMock.getPetName(),
                newPetRequestDtoMock.getPetGender(),
                newPetRequestDtoMock.getPetAge(),
                newPetRequestDtoMock.getPetVaccinated(),
                newPetRequestDtoMock.getPetHealthWarranty(),
                newPetRequestDtoMock.getPetTitle(),
                newPetRequestDtoMock.getPetDescription(),
                newPetRequestDtoMock.getPetNote(),
                newPetRequestDtoMock.getPetQuantity(),
                newPetRequestDtoMock.getPetPrice(),
                newPetRequestDtoMock.getPetPriceDiscount()
        );

        when(petRepositoryMock.findPetId(
                newPetRequestDtoMock.getCategoryId(),
                newPetRequestDtoMock.getPetName(),
                newPetRequestDtoMock.getPetGender(),
                newPetRequestDtoMock.getPetAge(),
                newPetRequestDtoMock.getPetVaccinated(),
                newPetRequestDtoMock.getPetHealthWarranty(),
                newPetRequestDtoMock.getPetTitle(),
                newPetRequestDtoMock.getPetDescription(),
                newPetRequestDtoMock.getPetNote(),
                newPetRequestDtoMock.getPetQuantity(),
                newPetRequestDtoMock.getPetPrice(),
                newPetRequestDtoMock.getPetPriceDiscount()
        )).thenReturn(findPetIdResponseDtoMock);

        when(newPetRequestDtoMock.getPetImage()).thenReturn(petImageMock);
        petImageMock.stream().forEach(image -> {
            verify(petRepositoryMock).addPetImage(findPetIdResponseDtoMock.getPetId(), image);
        });
    }

    @Test
    public void addNewPet_ThrowsException_When_PetRequestDtoInvalid() {
        PetRepository petRepositoryMock = mock(PetRepository.class);
        NewPetRequestDto newPetRequestDtoMock = mock(NewPetRequestDto.class);

        when(petRepositoryMock.addPet(
                null,                   //False value make error
                "Pet name",
                "Đực",
                "12 tháng",
                "Đã tiêm chủng",
                "Được bảo hành sức khỏe",
                "Pet title",
                "Pet description",
                "Pet note",
                12L,
                2500000L,
                2250000L
        )).thenThrow(new ApiRequestException("Error when add new pet"));

        petRepositoryMock.addPet(
                null,                   //False value make error
                "Pet name",
                "Đực",
                "12 tháng",
                "Đã tiêm chủng",
                "Được bảo hành sức khỏe",
                "Pet title",
                "Pet description",
                "Pet note",
                12L,
                2500000L,
                2250000L);
    }
}
