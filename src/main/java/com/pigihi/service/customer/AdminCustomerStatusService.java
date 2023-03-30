/**
 * 
 */
package com.pigihi.service.customer;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.clients.ClientCommunicator;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.EditCustomerModel;
import com.pigihi.service.DELETERequestSender;
import com.pigihi.service.PATCHRequestSender;
import com.pigihi.service.PUTRequestSender;
import com.pigihi.service.UserWriteService;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class AdminCustomerStatusService implements UserWriteService {

	@Override
	public String write(ClientCommunicator clientCommunicator) throws IOException, InterruptedException {
		String response = clientCommunicator.send();
		System.out.println("Response from customer service: " + response);
		return response;
	}
	
}
