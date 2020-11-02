<h1 align="center">Licensing System Discord</h1>
<div align="center">
  <strong> The discord bot for Licensing System</strong>
  
  <a href="https://github.com/SKATEB0ARD/licensing-system"> Licensing System</a>
</div>
<br />

# Installation
Clone the repository
```
git clone https://github.com/SKATEB0ARD/licensing-system.git
```
Go into folder and open up a command prompt and do
```
gradle build
```
Once built go into ```build/libs/``` and create a file called ```config.json```

Config Example
```json
{
  "api_url": "http://website.com/api/v1/",
  "api_token": "123-456-789-000",
  "discord_token": "token",
  "guild_id": "111111111111",
  "user_role_id": "11111111111111",
  "staff_role_id": "111111"
}
```

Then run the bot.
