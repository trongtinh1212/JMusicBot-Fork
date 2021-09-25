package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class BassBoostCmd extends DJCommand {

    public BassBoostCmd(Bot bot) {
        super(bot);
        this.name = "bassboost";
        this.help = "bassboost current track";
        this.arguments = "<on|off>";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.beListening = false;
        this.bePlaying = true;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();

        switch (event.getArgs()) {
            case "on":
                handler.enableBassboost(true);
                event.replySuccess("Enabled bassboost!");
                break;
            case "off":
                handler.enableBassboost(false);
                event.replySuccess("Disabled bassboost!");
                break;
            default:
                event.replyError("Not enough args! Type on|off");
                break;
        }
    }
}
