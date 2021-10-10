package com.goda.pageword.presentation.mappers

import com.goda.data.local.models.Word


fun Map<String, Int>.toWordsList( ):List<Word> =

    this.toList().map { pair: Pair<String, Int> ->  Word(pair.first,pair.second)  }
