package com.example.backendTestGym.controller.admin;

import com.example.backendTestGym.dto.RentalItemDTO;
import com.example.backendTestGym.service.RentalItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자 대여 물품")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminRentalItemController {
    private final RentalItemService rentalItemService;

    @Operation(summary = "새로운 대여 물품 등록")
    @ApiResponse(responseCode = "200", description = "새로운 대여 물품 등록 성공")
    @PostMapping("/rental")
    public ResponseEntity<RentalItemDTO> registerRentalItem(@RequestBody RentalItemDTO rentalItemDTO) {
        rentalItemService.addRentalItem(rentalItemDTO.getName());
        return ResponseEntity.status(HttpStatus.OK).body(rentalItemDTO);
    }

    @Operation(summary = "기존 대여물품 삭제")
    @ApiResponse(responseCode = "200", description = "대여물품 삭제 성공")
    @DeleteMapping("/rental")
    public ResponseEntity<RentalItemDTO> DeleteEquip(
            @RequestBody RentalItemDTO rentalItemDTO) {
        rentalItemService.deleteEquipment(rentalItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(rentalItemDTO);
    }
}
