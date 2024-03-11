package com.springboot.springbootref;

import com.springboot.springbootref.books.Book;
import com.springboot.springbootref.books.BookService;
import com.springboot.springbootref.colleges.College;
import com.springboot.springbootref.colleges.CollegeService;
import com.springboot.springbootref.exceptions.UserNotFoundException;
import com.springboot.springbootref.registration.RegisterRequest;
import com.springboot.springbootref.registration.Register;
import com.springboot.springbootref.registration.RegisterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springbootref.shops.Shops;
import com.springboot.springbootref.shops.ShopsService;
import com.springboot.springbootref.staff.Staff;
import com.springboot.springbootref.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.springboot.springbootref.LoggerUtil.getLogger;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class Controller {
    private final RegisterService registerService;
    @Autowired
    private final StaffService staffService;
    @Autowired
    private final ShopsService shopService;
    @Autowired
    private final CollegeService collegeService;
    @Autowired
    private final BookService bookService;

    public Controller(RegisterService registerService, StaffService staffService, ShopsService shopService, CollegeService collegeService, BookService bookService) {
        this.staffService= staffService;
        this.registerService = registerService;
        this.shopService = shopService;
        this.collegeService = collegeService;
        this.bookService = bookService;
    }
    @GetMapping("/registers")
    public List<Register> registers() {
        return registerService.registers();
    } // retrieving all users from the register Service.

    @GetMapping("/registers/{register_Id}")
    public Optional<Register> register(@PathVariable("register_Id") Integer Id) {
        return registerService.register(Id);
    } // retrieving single user from the register Service based on the id.

    @PostMapping("/registers")
    public ResponseEntity<String> saveRegister(@RequestBody RegisterRequest registerrequest) {

        System.out.println(registerrequest.username());

        String result = registerService.addRegister(registerrequest); // Adding user in Database using register service fetch the response in result variable
        ObjectMapper objectMapper = new ObjectMapper();
        int status = 400;

        try {
            JsonNode jsonNode = objectMapper.readTree(result);
            // Extract the "status" value
            status = jsonNode.get("status").asInt();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(status).body(result); // returning Response to post method
    }

    @DeleteMapping("/registers/{register_id}")
    public ResponseEntity<String> deleteRegister(@PathVariable("register_id") Integer Id) {
        boolean Success =registerService.deleteRegisterById(Id); // Delete the user details using register service deleteRegisterById return response.
        if(Success){
            return ResponseEntity.status(200).body("Deleted"); // If successfully deleted Sending response as OK.
        }
        else{
            return ResponseEntity.status(400).body("Unable to Delete");// If user not deleted Sending response as Bad Request.
        }
    }

    @PutMapping("/registers/{register_id}")
    public ResponseEntity<String> editRegister(@PathVariable("register_id") Integer Id,@RequestBody RegisterRequest registerRequest) {
        boolean Success =registerService.editRegisterById(Id,registerRequest); // Edit the user details using register service editRegisterById return response.
        if(Success){
            return ResponseEntity.status(200).body("Updated");// If successfully edited Sending response as OK.
        }
        else{
            return ResponseEntity.status(400).body("Unable to Update");// If record not edited Sending response as Bad Request.
        }
    }
    @GetMapping("/staffs")
    public List<Staff> getStaffs() {
        return staffService.allStaff();
    } // retrieving all users from the register Service.

    @GetMapping("/staffs/{Id}")
    public Staff staff(@PathVariable("Id") Long Id) {
        return staffService.getStaff(Id);
    }
    @PostMapping("/staffs")
    public ResponseEntity<?> saveStaff(@RequestBody Staff staff) {
        try {
            Staff createdPost = staffService.saveUser(staff);
            return ResponseEntity.ok(createdPost);
        } catch (UserNotFoundException e) {
            getLogger().error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/staffs/{id}/delete")
    public String delete(@PathVariable Long id) {
        staffService.deleteById(id);
        return "deleted";
    }
    @PutMapping("/staffs/{id}/edit")
    public Staff edit(@PathVariable Long id, @RequestBody Staff staff) {
        return staffService.edit(staff,id);
    }

    @PostMapping("/shops")
    public Shops createShops(@RequestBody Shops shop) {
        return shopService.save(shop);
    }

    @GetMapping("/shops/{id}")
    public Shops findShopById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @GetMapping("/shops")
    public List<Shops> findAllShops() {
        return shopService.findAll();
    }


    @DeleteMapping("shops/{id}/delete")
    public void deleteShopById(@PathVariable Long id) {
        shopService.deleteById(id);
    }
    @PutMapping("shops/{id}/edit")
    public Shops editShop(@PathVariable Long id, @RequestBody Shops shop){
        return shopService.edit(id,shop);
    }
    @PostMapping("/college")
    public College addCollege(@RequestBody College college) {
        return collegeService.save(college);
    }

    @GetMapping("/college/{id}")
    public College findCollegeById(@PathVariable Long id) {
        return collegeService.findById(id);
    }

    @GetMapping("/college")
    public List<College> findAllColleges() {
        return collegeService.findAll();
    }


    @DeleteMapping("college/{id}/delete")
    public void deleteCollegeById(@PathVariable Long id) {
        collegeService.deleteById(id);
    }
    @PutMapping("college/{id}/edit")
    public College editCollege(@PathVariable Long id, @RequestBody College col){
        return collegeService.edit(id,col);
    }

    //Books
    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @DeleteMapping("books/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id)?"Deleted":"Unable to delete";
    }
    @PutMapping("books/{id}/edit")
    public Book editBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.editBook(id,book);
    }
}
