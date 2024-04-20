package com.example.backendTestGym.controller.admin;

import com.example.backendTestGym.domain.Gym;
import com.example.backendTestGym.dto.GymDTO;
import com.example.backendTestGym.service.GymService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관리자 헬스장")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminGymController {

    private final GymService gymService;

    @Operation(summary = "헬스장 등록")
    @ApiResponse(responseCode = "200", description = "헬스장 등록 성공")
    @PostMapping("/gym")
    public ResponseEntity<GymDTO> registerGym(@RequestBody GymDTO gymDTO) {
        Gym gym = new Gym(gymDTO.getName());
        gymService.saveGym(gym);
        return ResponseEntity.status(HttpStatus.OK).body(gymDTO);
    }

    @Operation(summary = "헬스장 정보 수정")
    @ApiResponse(responseCode = "200", description = "헬스장 정보 수정 성공")
    @PutMapping("/gym/{id}/{newName}")
    public ResponseEntity<String> editGym(@PathVariable("id") Long id,
                                          @PathVariable("newName") String newName) {
        boolean updated = gymService.updateGym(id, newName);
        if (updated) {
            return ResponseEntity.ok("헬스장 정보 수정 성공");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "헬스장 삭제")
    @ApiResponse(responseCode = "200", description = "헬스장 삭제 성공")
    @DeleteMapping("/gym/{id}")
    public ResponseEntity<String> editGym(@PathVariable("id") Long id) {
        boolean deleted = gymService.deleteGymById(id);
        if (deleted) {
            return ResponseEntity.ok("헬스장 삭제 성공");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
