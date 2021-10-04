package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;
import com.jagrosh.jmusicbot.settings.Settings;

public class KaraokeCmd extends DJCommand {

    public KaraokeCmd(Bot bot) {
        super(bot);
        this.name = "karaoke";
        this.help = "apply karaoke filter to the current track";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.beListening = false;
        this.bePlaying = true;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();
        Settings settings = bot.getSettingsManager().getSettings(event.getGuild());

        if(!settings.getKaraoke()) {
            handler.enableKaraoke(true);
            event.replySuccess("Enabled `karaoke`!");
        } else {
            handler.enableKaraoke(false);
            event.replySuccess("Disabled `karaoke`!");
        }

    }
}
