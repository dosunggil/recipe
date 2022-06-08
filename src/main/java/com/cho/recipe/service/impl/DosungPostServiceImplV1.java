package com.cho.recipe.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cho.recipe.config.DosungPostConfig;
import com.cho.recipe.dao.DosungPostDao;
import com.cho.recipe.model.DosungCOOK;
import com.cho.recipe.model.DosungPostVO;
import com.cho.recipe.service.DosungPostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DosungPostServiceImplV1 implements DosungPostService {


	@Autowired
	private DosungPostDao postDao;
	/*
	 * API 가 아닌 DB 에서의 검색 결과 찾아주기.
	 */
	@Override
	public List<DosungPostVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 한 레시피에 대한 결과를 찾아주기.
	 */
	@Override
	public DosungPostVO findById(String id) {
		return postDao.findById(id);
	}

	@Override
	public int insert(DosungPostVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(DosungPostVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String queryString(String title) {

		String queryString = DosungPostConfig.API_URL;
		queryString += String.format("/%s", DosungPostConfig.API_ID);
		log.debug("현재 쿼리스트링 : " + queryString);

		String[] sp = title.split(" ");
	
		String encodeSearch = null;
		try {
			encodeSearch = URLEncoder.encode(sp[0], "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.debug("URL Encoding 오류 발생");
			return null;
		}
		queryString += String.format("/RCP_NM=%s", encodeSearch);
		return queryString;
	}

	@Override
	public String getJsonString(String queryString) {

		URL url = null;
		HttpURLConnection httpCon = null;

		try {
			url = new URL(queryString);
			httpCon = (HttpURLConnection) url.openConnection();

			httpCon.setRequestMethod("GET");

			int resCode = httpCon.getResponseCode();

			BufferedReader buffer = null;
			InputStreamReader is = null;

			if (resCode == 200) {
				is = new InputStreamReader(httpCon.getInputStream());
			} else {
				is = new InputStreamReader(httpCon.getErrorStream());
			}
			buffer = new BufferedReader(is);

			String retString = "";
			while (true) {
				String line = buffer.readLine();
				if (line == null)
					break;

				retString += line;
			}
			return retString;

		} catch (MalformedURLException e) {
			log.debug("Query String 문자열 오류");
			return null;
		} catch (IOException e) {
			log.debug("네트워크 연결을 할 수 없음");
			return null;
		}
	}

//	@Override
//	public List<Object> getRecipes(String queryString) {
//		URI restURI = null;
//
//		try {
//			restURI = new URI(queryString);
//		} catch (URISyntaxException e) {
//			log.debug("URI 오류 (getRecipes)");
//			return null;
//		}
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>("Parameter", headers);
//
//		RestTemplate restTemp = new RestTemplate();
//
//		ResponseEntity<DosungPostParent<DosungPostVO>> resData = null;
//		resData = restTemp.exchange(restURI, HttpMethod.GET, entity, new ParameterizedTypeReference<DosungPostParent<DosungPostVO>>() {} );
//	
//		log.debug(resData.getBody().toString());
//
//		return resData.getBody().row;
//	}
	
	@Override
	public List<DosungPostVO> getRecipes(String queryString) {
		URI restURI = null;

		try {
			restURI = new URI(queryString);
		} catch (URISyntaxException e) {
			log.debug("URI 오류 (getRecipes)");
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("Parameter", headers);

//		ResponseEntity<String> resData= null;
//		RestTemplate restTemp = new RestTemplate();
//		resData = restTemp.exchange(restURI, HttpMethod.GET, entity, String.class);
//		log.debug(resData.getBody().toString());

		ResponseEntity<DosungCOOK> resData = null;
		RestTemplate restTemp = new RestTemplate();
		resData = restTemp.exchange(restURI, HttpMethod.GET, entity, DosungCOOK.class);

// 		log.debug(resData.getBody().toString());
		return resData.getBody().COOKRCP01.row;
	
	}
}
	
