package com.boaglio.mongodb;

import java.util.List;

import org.jnosql.diana.api.Value;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.api.document.DocumentQuery;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

public class JUGsList {

	public static final String DATABASE = "test";
	public static final String DOCUMENT_COLLECTION = "jug";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {

		DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
		
		try (DocumentCollectionManagerFactory collectionFactory = configuration.get();) {
			DocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);
			DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
			DocumentCondition condition = DocumentCondition.eq(Document.of("region", "Sudeste"));
			query.and(condition);
			List<DocumentEntity> documentsFound = collectionManager.find(query);
			System.out.println("-------- MongoDB JUG List from REGION 4 (JNoSQL Diana) ------------");
			DocumentEntity region = documentsFound.get(0);
			Document jugs = region.getDocuments().get(2);
			System.out.println("JSON = "+jugs);
			
			System.out.println("-------- List<JUG> ------------");
			Value jugsList = jugs.getValue();
			List<org.bson.Document> list = (List<org.bson.Document>) jugsList.get();
			list.stream().forEach(System.out::println);
		}
	}
 
}
