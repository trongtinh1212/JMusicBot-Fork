package com.jagrosh.jmusicbot.lang;

import com.jagrosh.jmusicbot.Bot;

public class Lang {
    private LangName lang;
    private Bot bot;

    public Lang() {
    }

    public LangName getRegionLang(long guildid) {
        switch (bot.getJDA().getGuildById(guildid).getRegion()) {
            case RUSSIA:
                return LangName.RU;
            default:
                return LangName.ENG;
        }
    }
}