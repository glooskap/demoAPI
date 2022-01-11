package demo.spring.resource;

import demo.spring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = "api/demo/quotes")
public class ViewController {

    UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET
            , path = "/{quoteID}"
    )
    @Operation(summary = "GET request to read a specific quote",
            responses = {
                    @ApiResponse(responseCode = "200", description = "the specified quote"),
                    @ApiResponse(responseCode = "404", description = "Quote not found")
            }
    )
    public ResponseEntity<String> readQuote(@Parameter(description = "to specify the quote", required = true)
                                                @PathVariable("quoteID") int quoteID)
    {
        String tmp = userService.getQuote(quoteID);
        if (tmp == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no quote with id " + quoteID);
        return ResponseEntity.status(HttpStatus.OK).body(tmp);
    }

    @RequestMapping(
            method = RequestMethod.POST
            , consumes = MediaType.TEXT_PLAIN_VALUE
            , path = "/new"
    )
    @Operation(summary = "POST request to add a new quote",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Quote created"),
                    @ApiResponse(responseCode = "400", description = "BAD REQUEST")
            }
    )
    public ResponseEntity<String> addQuote(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "String value to be added")
                                               @RequestBody String quote)
    {
        if (userService.insertQuote(quote) == 1)
            return ResponseEntity.status(HttpStatus.CREATED).body("Quote stored successfully");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quote could not be stored");
    }

    @RequestMapping(
            method = RequestMethod.PUT
            , consumes = MediaType.TEXT_PLAIN_VALUE
            , path = "/{quoteID}"
    )
    @Operation(summary = "PUT request to update a specific quote",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Quote updated"),
                    @ApiResponse(responseCode = "404", description = "Quote not found")
            }
    )
    public ResponseEntity<String> updateQuote(@Parameter(name = "id", description = "to specify the quote" , required = true)
                                                  @PathVariable("quoteID") int quoteID,
                                              @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "String value to become the new quote body")
                                              @RequestBody String quote)
    {
        if (userService.updateQuote(quoteID, quote) == -1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no quote with id= " + quoteID);
        return ResponseEntity.status(HttpStatus.OK).body("Quote updated");
    }

    @RequestMapping(
            method = RequestMethod.DELETE
            , path = "/{quoteID}"
    )
    @Operation(summary = "DELETE request to delete a quote",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Quote deleted"),
                    @ApiResponse(responseCode = "404", description = "Quote not found")
            }
    )
    public ResponseEntity<String> deleteQuote(@Parameter(name = "id", description = "to specify the quote" , required = true)
                                                  @PathVariable("quoteID") int quoteID)
    {
        if (userService.deleteQuote(quoteID) == -1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no quote with id= " + quoteID);
        return ResponseEntity.status(HttpStatus.OK).body("Quote deleted");
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    @Operation(summary = "GET request to read the whole collection of quotes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "the quotes collection"),
                    @ApiResponse(responseCode = "400", description = "BAD REQUEST")
            }
    )
    public ResponseEntity<String> fetchQuotes() {
        String response = userService.getAllQuotes();
        if (response == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
