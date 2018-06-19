package com.ccizer.transactionsstatistics.constant;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.TreeMultimap;

public class TransactionConstants {

    public static final Multimap<Long, Double> MAP_OF_TRANSACTIONS =
            Multimaps.synchronizedMultimap(TreeMultimap.create());

    public static final long SIXTY_SECONDS = 60;

    private TransactionConstants() {
    }
}