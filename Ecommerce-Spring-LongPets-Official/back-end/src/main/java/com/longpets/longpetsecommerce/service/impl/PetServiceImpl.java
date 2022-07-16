package com.longpets.longpetsecommerce.service.impl;

import com.longpets.longpetsecommerce.data.model.Category;
import com.longpets.longpetsecommerce.data.model.Image;
import com.longpets.longpetsecommerce.data.model.Pet;
import com.longpets.longpetsecommerce.data.repository.CategoryRepository;
import com.longpets.longpetsecommerce.data.repository.ImageRepository;
import com.longpets.longpetsecommerce.data.repository.PetRepository;
import com.longpets.longpetsecommerce.dto.request.NewCategoryRequestDto;
import com.longpets.longpetsecommerce.dto.request.NewPetRequestDto;
import com.longpets.longpetsecommerce.dto.request.UpdatePetRequestDto;
import com.longpets.longpetsecommerce.dto.response.*;
import com.longpets.longpetsecommerce.exception.ApiRequestException;
import com.longpets.longpetsecommerce.exception.ResourceNotFoundException;
import com.longpets.longpetsecommerce.service.PetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final ModelMapper modelMapper;

//    @Override
//    public List<AllPetSearchByNameResponseDto> getAllPetSearchByName(String petName) {
//        List<AllPetSearchByNameResponseDto> allPetSearchByNameResponseDtos = new ArrayList<>();
//        try {
//            allPetSearchByNameResponseDtos = petRepository.getAllPetSearchByName(petName);
//        } catch(Exception e) {
////            throw new ApiRequestException("Loi tim kiem");
//        throw e;
//        }
//        return allPetSearchByNameResponseDtos;
//    }

//    @Override
//    public List<PetAndCategoryByPetIdResponseDto> getPetAndCategoryByPetId(Long petId) {
//        return petRepository.getPetAndCategoryByPetId(petId);
//    }

//    @Override
//    public List<PetNameAndCategoryTitleByCategoryIdResponseDto> getPetNameAndCategoryTitleByCategoryId(Long categoryId) {
//        return petRepository.getPetNameAndCategoryTitleByCategoryId(categoryId);
//    }

//    @Override
//    public void updatePet(UpdatePetRequestDto updatePetRequestDto) {
//        try {
//            petRepository.updatePet(updatePetRequestDto.getCategoryId(),
//                    updatePetRequestDto.getPetName(),
//                    updatePetRequestDto.getPetGender(),
//                    updatePetRequestDto.getPetAge(),
//                    updatePetRequestDto.getPetVaccinated(),
//                    updatePetRequestDto.getPetHealthWarranty(),
//                    updatePetRequestDto.getPetTitle(),
//                    updatePetRequestDto.getPetDescription(),
//                    updatePetRequestDto.getPetNote(),
//                    updatePetRequestDto.getPetQuantity(),
//                    updatePetRequestDto.getPetPrice(),
//                    updatePetRequestDto.getPetPriceDiscount(),
//                    updatePetRequestDto.getPetId());
//            try {
//                petRepository.deletePetImage(updatePetRequestDto.getPetId());
//                try {
//                    List<String> petImage = updatePetRequestDto.getPetImage();
//                    petImage.stream().forEach(image -> {
//                        petRepository.addPetImage(updatePetRequestDto.getPetId(), image);
//                    });
//                }catch (Exception exception) {
//                    throw new ApiRequestException("Error when add new pet image");
//                }
//            } catch (Exception exception) {
//                throw new ApiRequestException("Error when delete old pet image");
//            }
//        } catch (Exception exception) {
//            throw new ApiRequestException("Error when update pet");
//        }
//    }

//    @Override
//    public List<PetAndCategoryAndImageByPetIdResponseDto> getAllPetCategoryImageByPetId(Long petId) {
//        return petRepository.getPetAndCategoryAndImageByPetId(petId);
//    }

//    @Override
//    public List<AllPetImageResponseDto> getAllPetImage(Long petId) {
//        return petRepository.getAllPetImage(petId);
//    }

//    @Override
//    public void addNewPet(NewPetRequestDto newPetRequestDto) {
//        try {
//           petRepository.addPet(newPetRequestDto.getCategoryId(),
//                   newPetRequestDto.getPetName(),
//                   newPetRequestDto.getPetGender(),
//                   newPetRequestDto.getPetAge(),
//                   newPetRequestDto.getPetVaccinated(),
//                   newPetRequestDto.getPetHealthWarranty(),
//                   newPetRequestDto.getPetTitle(),
//                   newPetRequestDto.getPetDescription(),
//                   newPetRequestDto.getPetNote(),
//                   newPetRequestDto.getPetQuantity(),
//                   newPetRequestDto.getPetPrice(),
//                   newPetRequestDto.getPetPriceDiscount());
//           try {
//               FindPetIdResponseDto findPetIdResponseDto = petRepository.findPetId(newPetRequestDto.getCategoryId(),
//                       newPetRequestDto.getPetName(),
//                       newPetRequestDto.getPetGender(),
//                       newPetRequestDto.getPetAge(),
//                       newPetRequestDto.getPetVaccinated(),
//                       newPetRequestDto.getPetHealthWarranty(),
//                       newPetRequestDto.getPetTitle(),
//                       newPetRequestDto.getPetDescription(),
//                       newPetRequestDto.getPetNote(),
//                       newPetRequestDto.getPetQuantity(),
//                       newPetRequestDto.getPetPrice(),
//                       newPetRequestDto.getPetPriceDiscount());
//               try {
//                   List<String> petImage = newPetRequestDto.getPetImage();
//                   petImage.stream().forEach(image -> {
//                       petRepository.addPetImage(findPetIdResponseDto.getPetId(), image);
//                   });
//               }catch (Exception exception) {
//                   throw new ApiRequestException("Error when add new pet image");
//               }
//           } catch (Exception exception) {
//               throw new ApiRequestException("Can't find pet id");
//           }
//        } catch (Exception exception) {
//            throw new ApiRequestException("Error when add new pet");
//        }
//    }

//    @Override
//    public void deletePet(Long petId) {
//        try {
//            petRepository.deletePetImage(petId);
//            try {
//                petRepository.deletePetOrderDetail(petId);
//                try {
//                    petRepository.deletePet(petId);
//                } catch (Exception exception) {
//                    throw new ApiRequestException("Error when delete pet");
//                }
//            } catch (Exception exception) {
//                throw new ApiRequestException("Error when delete order detail");
//            }
//        } catch (Exception exception) {
//            throw new ApiRequestException("Error when delete pet image");
//        }
//    }

//    @Override
//    public List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImage() {
//        return petRepository.getPetAndCategoryAndImage();
//    }

//    @Override
//    public List<PetAndCategoryAndImageResponseDto> getPetAndCategoryAndImageByPetName(String petName) {
//        return petRepository.getPetAndCategoryAndImageByPetName(petName);
//    }

//    @Override
//    public PetQuantityCountResponseDto getPetQuantityCount() {
//        return petRepository.getPetQuantityCount();
//    }

//  ================================ FIX =============================
    @Override
    public List<PetResponseDto> findAllPet() {
        List<Pet> pets = petRepository.findAll();
        List<PetResponseDto> petResponseDtos = modelMapper.map(pets,
                new TypeToken<List<PetResponseDto>>() {
                }.getType());
        return petResponseDtos;
    }

    @Override
    public List<PetResponseDto> getAllPetSearchByName(String petName) {
        List<Pet> pets = petRepository.findPetByPetNameContaining(petName);
        List<PetResponseDto> petResponseDtos = modelMapper.map(pets,
                new TypeToken<List<PetResponseDto>>() {
                }.getType());
        return petResponseDtos;
    }

    @Override
    public PetResponseDto getPetAndCategoryByPetId(Long petId) {
        Pet pet = petRepository.findPetByPetId(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find pet id "+ petId));
        PetResponseDto petResponseDto = modelMapper.map(pet, PetResponseDto.class);
        return petResponseDto;
    }

    @Override
    public List<PetNameResponseDto> getAllPetName(Long categoryId) {
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find category id "+categoryId));
        String categoryTitle = category.getCategoryTitle();
        List<Pet> pets = petRepository.findPetByCategoryPet(category);
        List<String> petNames = new ArrayList<>();
        pets.stream().forEach(pet -> {
            String petName = pet.getPetName();
            if(!petNames.contains(petName)) {
                petNames.add(petName);
            }
        });
        List<PetNameResponseDto> petNameResponseDtos = new ArrayList<>();
        petNames.stream().forEach(petName -> {
            PetNameResponseDto petNameResponseDto = new PetNameResponseDto();
            petNameResponseDto.setPetName(petName);
            petNameResponseDto.setCategoryTitle(categoryTitle);
            petNameResponseDtos.add(petNameResponseDto);
        });
        return petNameResponseDtos;
    }

    @Override
    public PetCountResponseDto getPetCount() {
        Long petCount = petRepository.count();
        PetCountResponseDto petCountResponseDto = new PetCountResponseDto();
        petCountResponseDto.setPetQuantityCount(petCount);
        return petCountResponseDto;
    }

    @Override
    public PetResponseDto addPet(NewPetRequestDto newPetRequestDto) {
//        Set categoryPet
        Long categoryId = newPetRequestDto.getCategoryId();
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find category id " + categoryId));
        Pet pet = modelMapper.map(newPetRequestDto, Pet.class);
        pet.setCategoryPet(category);

//        Get Pet after add. Set images
        pet = petRepository.saveAndFlush(pet);
        List<String> petImageFromDto = newPetRequestDto.getPetImage();
        List<Image> images = new ArrayList<>();
        for (String imageString : petImageFromDto) {
            Image image = new Image();
            image.setImageContent(imageString);
            image.setPetImage(pet);
            images.add(image);
        }
        imageRepository.saveAll(images);

//        Return Pet response
        PetResponseDto petResponseDto = modelMapper.map(pet, PetResponseDto.class);
        petResponseDto.setImages(images);
        return petResponseDto;
    }

    @Override
    public PetResponseDto updatePet(UpdatePetRequestDto updatePetRequestDto) {
        Long petId = updatePetRequestDto.getPetId();
        Pet pet = petRepository.findPetByPetId(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find pet id "+petId));
        modelMapper.map(updatePetRequestDto, pet);

//         Set new categoryPet
        Long categoryId = updatePetRequestDto.getCategoryId();
        Category category = categoryRepository.findCategoryByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find category id" + categoryId));
        pet.setCategoryPet(category);

//         Delete old pet image and add new image
        List<Image> imagesOld = pet.getImages();
        imageRepository.deleteAllInBatch(imagesOld);

        List<String> petImageFromDto = updatePetRequestDto.getPetImage();
        List<Image> images = new ArrayList<>();
        for (String imageString : petImageFromDto) {
            Image image = new Image();
            image.setImageContent(imageString);
            image.setPetImage(pet);
            images.add(image);
        }
        imageRepository.saveAll(images);

//        Return petResponseDto
        pet = petRepository.save(pet);
        PetResponseDto petResponseDto = modelMapper.map(pet, PetResponseDto.class);
        return petResponseDto;
    }

    @Override
    public void deletePet(Long categoryId) {
        petRepository.deleteById(categoryId);
    }
}
