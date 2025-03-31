package utils;

import dtos.RegisterUserDto;

import java.util.UUID;

public class RegisterUserTestData {

    RegisterUserDto registerUserDto = new RegisterUserDto();

    public static RegisterUserDto getValidUserData() {
        RegisterUserDto data = new RegisterUserDto();
        data.setEmail(generateRandomTestEmail());
        data.setPassword("Password123");
        data.setFirstName("Automation");
        data.setLastName("User");
        data.setPassword("Password123");
        data.setTelephone("123-123-1234");

        return data;
    }

    public static RegisterUserDto getEmptyPasswordUserData() {
        RegisterUserDto data = new RegisterUserDto();
        data.setEmail(generateRandomTestEmail());
        data.setPassword("Password123");
        data.setFirstName("Automation");
        data.setLastName("User");
        data.setPassword("");
        data.setTelephone("123-123-1234");

        return data;
    }

    public RegisterUserDto getInvalidAddressData() {
        RegisterUserDto data = new RegisterUserDto();

        return data;
    }

    private static String generateRandomTestEmail() {
        String testEmail = "Automation_" + UUID.randomUUID() + "@gmail.com";
        return testEmail;
    }
}
