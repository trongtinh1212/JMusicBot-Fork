package com.jagrosh.jmusicbot.lang;

import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.utils.OtherUtil;
import net.dv8tion.jda.api.Region;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Lang {
    private long guildid;
    private JSONObject custom;
    private JSONObject eng;
    private JSONObject ru;
    private LangName lang;
    private Bot bot;

    public Lang(long guildid)  {
        try {
            this.guildid = guildid;
            this.ru = new JSONObject(new String(Files.readAllBytes(OtherUtil.getPath("ru.json"))));
            this.eng = new JSONObject(new String(Files.readAllBytes(OtherUtil.getPath("eng.json"))));
            this.custom = new JSONObject(new String(Files.readAllBytes(OtherUtil.getPath("custom.json"))));
        } catch(IOException e) {
            LoggerFactory.getLogger("MultiLang").warn("Failed to load lang files: "+e);
        }
    }

    public JSONObject getRegionLangObjct(long guildid) {
        if (Objects.requireNonNull(bot.getJDA().getGuildById(this.guildid)).getRegion() == Region.RUSSIA) {
            return this.ru;
        }
        return this.eng;
    }

    public boolean langSelected(long guildid) {
        return bot.getSettingsManager().getSettings(this.guildid).getGuildLang() != null;
    }

    public JSONObject getJsonTranslateObject(long guildid) {
        if(!langSelected(this.guildid)) {
           return getRegionLangObjct(this.guildid);
        } else {
            switch (bot.getSettingsManager().getSettings(this.guildid).getGuildLang()) {
                case RU:
                    return this.ru;
                case ENG:
                    return this.eng;
                case CUSTOM:
                    return this.custom;
            }
        }
        return this.eng;
    }

    public String getString(String string) {
       return getJsonTranslateObject(guildid).getString(string);
    }

}