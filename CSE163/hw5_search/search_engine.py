"""
Kim-Long Do
5/4/21
Take-Home Assessment # 4
Search
TA: Zoe, Ryan

This file represents a corpus of Document objects and calculates the
tf-idf statistic between each document and a given query
"""

import os
import re
import math
from document import Document


class SearchEngine:
    """
    This class represents a corpus of Document objects and calculates the
    tf-idf statistic between each document and a given query
    """

    def __init__(self, dir_name):
        """
        Implements the initializer for the SearchEngine class. Takes in a
        directory of documents and constructs an inverted index of the
        documents.
        """
        self._inverted_index = {}
        self._list_files = []
        file_names = os.listdir(dir_name)
        for file_name in file_names:
            self._list_files.append(os.path.join(dir_name, file_name))
        for doc in self._list_files:
            doc_info = Document(doc)
            for term in doc_info.get_words():
                if term in self._inverted_index:
                    self._inverted_index[term].add(doc_info)
                else:
                    self._inverted_index[term] = {doc_info}

    def _calculate_idf(self, term):
        """
        Returns the inverse document frequency for a term
        Returns 0 if the term is not within the inverted index
        Term's letter-cases and punctuations are ignored
        """
        if term not in self._inverted_index:
            return 0
        total_docs = len(self._list_files)
        term_docs = len(self._inverted_index[term])
        return math.log(total_docs/term_docs)

    def search(self, terms):
        """
        Takes in a query of term(s). Returns a descending sorted list of
        documents based on the tf-idf statistics of the query.
        Returns an empty list if no document matches the search query.
        Terms' letter-cases and punctuations are ignored
        """
        dict_docs = {}
        for term in terms.split():
            term = term.lower()
            term = re.sub(r'\W+', '', term)
            doc_info = self._inverted_index.get(term)
            if doc_info is not None:
                for doc in doc_info:
                    if term in doc.get_words():
                        doc_idf = self._calculate_idf(term)
                        doc_tf = doc.term_frequency(term)
                        tfidf = doc_tf * doc_idf
                        if doc.get_path() not in dict_docs:
                            dict_docs[doc.get_path()] = tfidf
                        else:
                            dict_docs[doc.get_path()] += tfidf
            list_docs = dict_docs.items()
            list_docs = sorted(list_docs, key=lambda x: (-x[1], x[0]))
        return [i[0] for i in list_docs]
