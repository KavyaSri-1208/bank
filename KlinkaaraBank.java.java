// KlinkaaraBank.java
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class KlinkaaraBank {

    public static void main(String[] args) throws IOException {
        // Create an HTTP server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Context for the root path ("/") – the main bank page
        server.createContext("/", new BankHomeHandler());

        // Context for a simple health check
        server.createContext("/health", exchange -> {
            String response = "OK";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        // Start the server
        server.setExecutor(null); // use the default executor
        server.start();
        System.out.println("Klinkaara Bank server started on port 8080");
    }

    // Handler that returns the main bank web page
    static class BankHomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Klinkaara Bank</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
                            min-height: 100vh;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 20px;
                        }
                        .container {
                            background: white;
                            border-radius: 20px;
                            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                            padding: 50px 40px;
                            max-width: 600px;
                            width: 100%;
                            text-align: center;
                        }
                        .logo {
                            font-size: 2.5rem;
                            font-weight: 800;
                            color: #1e3c72;
                            margin-bottom: 5px;
                            letter-spacing: 1px;
                        }
                        .logo span {
                            color: #f39c12;
                        }
                        .tagline {
                            color: #666;
                            font-size: 0.95rem;
                            margin-bottom: 35px;
                            font-style: italic;
                        }
                        .balance-card {
                            background: linear-gradient(135deg, #1e3c72, #2a5298);
                            color: white;
                            border-radius: 15px;
                            padding: 30px;
                            margin-bottom: 30px;
                        }
                        .balance-label {
                            font-size: 0.85rem;
                            text-transform: uppercase;
                            letter-spacing: 2px;
                            opacity: 0.8;
                            margin-bottom: 10px;
                        }
                        .balance-amount {
                            font-size: 2.8rem;
                            font-weight: 700;
                        }
                        .balance-amount small {
                            font-size: 1rem;
                            font-weight: 400;
                            opacity: 0.8;
                        }
                        .actions {
                            display: grid;
                            grid-template-columns: 1fr 1fr;
                            gap: 15px;
                            margin-top: 10px;
                        }
                        .btn {
                            padding: 15px;
                            border: none;
                            border-radius: 10px;
                            font-size: 1rem;
                            font-weight: 600;
                            cursor: pointer;
                            transition: transform 0.2s, box-shadow 0.2s;
                        }
                        .btn:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
                        }
                        .btn-primary {
                            background: #f39c12;
                            color: white;
                        }
                        .btn-secondary {
                            background: #ecf0f1;
                            color: #1e3c72;
                        }
                        .footer {
                            margin-top: 30px;
                            font-size: 0.8rem;
                            color: #999;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="logo">Klinkaara <span>Bank</span></div>
                        <div class="tagline">Your Trusted Financial Partner</div>

                        <div class="balance-card">
                            <div class="balance-label">Available Balance</div>
                            <div class="balance-amount">$12,450.75 <small>USD</small></div>
                        </div>

                        <div class="actions">
                            <button class="btn btn-primary" onclick="alert('Transfer feature coming soon!')">Transfer</button>
                            <button class="btn btn-secondary" onclick="alert('Payment feature coming soon!')">Pay Bills</button>
                            <button class="btn btn-secondary" onclick="alert('Statement feature coming soon!')">Statement</button>
                            <button class="btn btn-secondary" onclick="alert('Support feature coming soon!')">Support</button>
                        </div>

                        <div class="footer">
                            &copy; 2025 Klinkaara Bank. All rights reserved.
                        </div>
                    </div>
                </body>
                </html>
                """;

            // Set response headers
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            byte[] responseBytes = html.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        }
    }
}