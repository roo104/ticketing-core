package dk.unwire.ticketing.core.domain.product.rest;

import dk.unwire.ticketing.core.domain.product.rest.model.ProductListResponse;
import dk.unwire.ticketing.core.domain.product.rest.model.ProductResponse;
import dk.unwire.ticketing.spring.rest.common.response.GenericResponseInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/{applicationId}/product")
public class ProductController {

    @RequestMapping(path = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductListResponse> getProducts(@PathVariable int applicationId) {
        GenericResponseInfo responseInfo = GenericResponseInfo.OK;

        return new ResponseEntity<>(new ProductListResponse(responseInfo), responseInfo.getHttpStatus());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ProductResponse> getProduct(@PathVariable int applicationId, @PathVariable long productId) {
        GenericResponseInfo responseInfo = GenericResponseInfo.OK;

        return new ResponseEntity<>(new ProductResponse(responseInfo), responseInfo.getHttpStatus());
    }
}
