//package com.example.iticketfinal.dto.company;
//
//import com.example.iticketfinal.dao.entity.EventEntity;
//import com.example.iticketfinal.dao.entity.PhoneEntity;
//import com.example.iticketfinal.dto.event.EventReqDto;
//import com.example.iticketfinal.dto.image.ImageDto;
//import com.example.iticketfinal.dto.phone.PhoneReqDto;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class CompanyLoginReqDto {
//    @NotNull
//    private String name;
//    @Email
//    private String email;
//    @NotNull
//    private MultipartFile logoImage;
//    private String website;
//    private String description;
//    private List<PhoneReqDto> phones;
//}
//
//
package com.example.iticketfinal.dto.company;

        import com.example.iticketfinal.dao.entity.EventEntity;
        import com.example.iticketfinal.dao.entity.PhoneEntity;
        import com.example.iticketfinal.dto.event.EventReqDto;
        import com.example.iticketfinal.dto.image.ImageDto;
        import com.example.iticketfinal.dto.phone.PhoneReqDto;
        import jakarta.validation.constraints.Email;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.springframework.web.multipart.MultipartFile;

        import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLoginReqDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotNull
    private ImageDto logo;
    private String website;
    private String description;
    private List<PhoneReqDto> phones;
}