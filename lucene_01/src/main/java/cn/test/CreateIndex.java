/**
 * Copyright (C), 2015-2018
 * FileName: CreateIndex
 * Author:   AK
 * Date:     2018/7/29 19:16
 * Description:
 * History:
 */

package cn.test;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/7/29
 * @since 1.0.0
 */
public class CreateIndex {
    @Test
    public void create() throws Exception {
        String path = "D:/index";
        Directory directory = FSDirectory.open(new File(path));

        // StandardAnalyzer analyzer = new StandardAnalyzer();

        // ik分词器
        IKAnalyzer analyzer = new IKAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, analyzer);

        IndexWriter indexWriter = new IndexWriter(directory,config);

        Document doc = new Document();

        doc.add(new StringField("id","u001", Field.Store.NO));
        doc.add(new TextField("title","传智播客官网-一样的教育,不一样的品质",Field.Store.YES));
        doc.add(new TextField("desc","传智播客专注IT培训,Java培训,人工智能+Python培训,PHP培训,C++培训,大数据培训,UI设计培训,移动开发培训,网络营销培训,web前端培训,全栈工程师培训,产品经理培训。",Field.Store.YES));
        doc.add(new TextField("content","呵呵",Field.Store.NO));

        Document doc1 = new Document();
        doc1.add(new StringField("id","u002", Field.Store.NO));
        doc1.add(new TextField("title","传智播客课程培训师资库",Field.Store.YES));
        doc1.add(new TextField("desc","传智播客师资库聚集众多传智播客JavaEE,Android,PHP,iOS,UI设计, 前端与移动开发, C/C++,网络营销, 游戏开发,云计算讲师大牛,传智播客讲师团队累计上百人,在IT...",Field.Store.YES));
        doc1.add(new TextField("content","呵呵",Field.Store.NO));

        indexWriter.addDocument(doc);
        indexWriter.addDocument(doc1);
        indexWriter.commit();

    }

    public void get(Query query) throws Exception {
        String path = "D:/index";
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(new File(path)));

        IndexSearcher searcher = new IndexSearcher(directoryReader);

        // QueryParser queryParser = new QueryParser("title",new IKAnalyzer());

        TopDocs docs = searcher.search(query, 10);

        System.out.println("命中数:"+docs.totalHits);

        ScoreDoc[] scoreDocs = docs.scoreDocs;

        for (ScoreDoc scoreDoc : scoreDocs) {
            float score = scoreDoc.score;
            int docId = scoreDoc.doc;
            System.out.println("文档Id:"+docId+" ,得分:" + score);

            Document myDoc = searcher.doc(docId);
            System.out.println("文档域字段id:"+myDoc.get("id"));
            System.out.println("文档域字段title:"+myDoc.get("title"));
            System.out.println("文档域字段desc:"+myDoc.get("desc"));
            System.out.println("文档域字段content:"+myDoc.get("content"));
            System.out.println("----------");
        }

    }

    @Test
    public void test2() throws Exception {
        String qName = "游戏开发";

        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(
                new String[]{"title","desc"},
                new IKAnalyzer()
        );

        Query query = multiFieldQueryParser.parse(qName);

        this.get(query);
    }

    @Test
    public void test3() throws Exception {
        String qName = "传智播客";

        TermQuery query = new TermQuery(new Term("title",qName));

        this.get(query);
    }

    @Test
    public void test4() throws Exception {
        String qName = "传智教室";
        // 相似度查询(最多改变两个)
        FuzzyQuery query = new FuzzyQuery(new Term("title",qName));

        this.get(query);
    }

}